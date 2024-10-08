package com.xxh.summary.lifecycle.source.savedstate.ktx

import android.view.View
import com.xxh.summary.lifecycle.source.savedstate.SavedStateRegistryOwner
import com.xxh.summary.lifecycle.source.savedstate.findViewTreeSavedStateRegistryOwner

/**
 * Locates the [SavedStateRegistryOwner] associated with this [View], if present.
 * This may be used to save and restore the state associated with this view.
 *
 */
@Deprecated(
    message = "Replaced by View.findViewTreeSavedStateRegistryOwner() from savedstate module",
    replaceWith = ReplaceWith(
        "findViewTreeSavedStateRegistryOwner()",
        "androidx.savedstate.findViewTreeSavedStateRegistryOwner"
    ),
    level = DeprecationLevel.HIDDEN
)
public fun View.findViewTreeSavedStateRegistryOwner(): SavedStateRegistryOwner? =
    findViewTreeSavedStateRegistryOwner()