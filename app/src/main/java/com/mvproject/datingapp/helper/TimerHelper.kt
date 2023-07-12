/*
 * Create by Medvediev Viktor
 * last update: 28.06.23, 15:29
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.helper

import android.os.CountDownTimer
import com.mvproject.datingapp.data.state.TimerState
import com.mvproject.datingapp.utils.CODE_TIMER_VALUE
import com.mvproject.datingapp.utils.DIVIDER_SECOND
import com.mvproject.datingapp.utils.TIMER_INTERVAL
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimerHelper @Inject constructor() {
    private val _timerState = MutableStateFlow(TimerState())
    val timerState = _timerState.asStateFlow()

    private var timer: CountDownTimer = createTimer()

    fun launchTimer() {
        startTimer()
    }

    private fun startTimer() {
        _timerState.update {
            it.copy(isTimerEnabled = true)
        }
        timer.start()
    }

    private fun createTimer(initial: Long = CODE_TIMER_VALUE) =
        object : CountDownTimer(initial, TIMER_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                _timerState.update {
                    it.copy(timeLeft = millisUntilFinished / DIVIDER_SECOND)
                }
            }

            override fun onFinish() {
                _timerState.update {
                    it.copy(isTimerEnabled = false)
                }
            }
        }
}