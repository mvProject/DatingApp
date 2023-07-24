/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 14:42
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.utils.LONG_ZERO

data class UserActivation(
    val status: Boolean = false,
    val period: Long = LONG_ZERO
)