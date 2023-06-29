/*
 * Create by Medvediev Viktor
 * last update: 28.06.23, 15:26
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.state

import com.mvproject.datingapp.utils.LONG_ZERO

data class TimerState(
    val isTimerEnabled: Boolean = false,
    val timeLeft: Long = LONG_ZERO
)