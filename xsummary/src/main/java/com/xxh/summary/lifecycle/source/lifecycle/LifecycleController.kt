package com.xxh.summary.lifecycle.source.lifecycle


import androidx.annotation.MainThread
import com.xxh.summary.lifecycle.source.lifecycle.DispatchQueue
import com.xxh.summary.lifecycle.source.lifecycle.Lifecycle
import kotlinx.coroutines.Job

/**
 * Attaches to a lifecycle and controls the [DispatchQueue]'s execution.
 */
@MainThread
internal class LifecycleController(
    private val lifecycle: Lifecycle,
    private val minState: Lifecycle.State,
    private val dispatchQueue: DispatchQueue,
    parentJob: Job
) {
    private val observer = LifecycleEventObserver { source, _ ->
        if (source.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            // cancel job before resuming remaining coroutines so that they run in cancelled
            // state
            handleDestroy(parentJob)
        } else if (source.lifecycle.currentState < minState) {
            dispatchQueue.pause()
        } else {
            dispatchQueue.resume()
        }
    }

    init {
        // If Lifecycle is already destroyed (e.g. developer leaked the lifecycle), we won't get
        // an event callback so we need to check for it before registering
        // see: b/128749497 for details.
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            handleDestroy(parentJob)
        } else {
            lifecycle.addObserver(observer)
        }
    }

    @Suppress("NOTHING_TO_INLINE") // avoid unnecessary method
    private inline fun handleDestroy(parentJob: Job) {
        parentJob.cancel()
        finish()
    }

    /**
     * Removes the observer and also marks the [DispatchQueue] as finished so that any remaining
     * runnables can be executed.
     */
    @MainThread
    fun finish() {
        lifecycle.removeObserver(observer)
        dispatchQueue.finish()
    }
}