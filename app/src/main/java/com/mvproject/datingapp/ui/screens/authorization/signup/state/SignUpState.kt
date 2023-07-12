/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signup.state

import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STEP_1

enum class SignUpState {
    EMAIL,
    CODE_VERIFY,
    PASSWORD,
    GENDER,
    NAME,
    DATE,
    INTEREST,
    LOCATION,
    PHOTO;

    companion object {
        fun SignUpState.nextState(): SignUpState {
            val currentIndex = this.ordinal
            return if (currentIndex == SignUpState.values().size - STEP_1) {
                this
            } else {
                val nextIndex = currentIndex + STEP_1
                SignUpState.values()[nextIndex]
            }
        }

        fun SignUpState.previousState(): SignUpState {
            val currentIndex = this.ordinal
            return if (currentIndex == INT_ZERO) {
                this
            } else {
                val nextIndex = currentIndex - STEP_1
                SignUpState.values()[nextIndex]
            }
        }

        fun SignUpState.isStartState(): Boolean {
            val currentIndex = this.ordinal
            return currentIndex == INT_ZERO
        }

        fun SignUpState.isLastState(): Boolean {
            val currentIndex = this.ordinal
            return currentIndex == SignUpState.values().size - STEP_1
        }

        fun SignUpState.completeProgress(): Float {
            val current = this.ordinal + STEP_1
            val total = SignUpState.values().size
            return current / total.toFloat()
        }
    }
}