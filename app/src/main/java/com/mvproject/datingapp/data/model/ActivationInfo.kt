/*
 * Create by Medvediev Viktor
 * last update: 08.08.23, 19:27
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvproject.datingapp.utils.INT_ZERO

data class ActivationInfo(
    @StringRes val title: Int = INT_ZERO,
    @StringRes val description: Int = INT_ZERO,
    @DrawableRes val logo: Int = INT_ZERO
)