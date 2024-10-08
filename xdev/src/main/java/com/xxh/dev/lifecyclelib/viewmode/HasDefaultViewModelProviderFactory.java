/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xxh.dev.lifecyclelib.viewmode;

import androidx.annotation.NonNull;

/**
 * Interface that marks a {@link ViewModelStoreOwner} as having a default
 * {@link ViewModelProvider.Factory} for use with
 * {@link ViewModelProvider#ViewModelProvider(ViewModelStoreOwner)}.
 */
public interface HasDefaultViewModelProviderFactory {
    /**
     * Returns the default {@link ViewModelProvider.Factory} that should be
     * used when no custom {@code Factory} is provided to the
     * {@link ViewModelProvider} constructors.
     *
     * @return a {@code ViewModelProvider.Factory}
     */
    @NonNull
    ViewModelProvider.Factory getDefaultViewModelProviderFactory();
}
