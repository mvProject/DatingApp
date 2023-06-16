/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 17:59
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.state

import timber.log.Timber

enum class ProfileCreateState {
    SELECT_GENDER,
    SELECT_NAME,
    SELECT_DATE,
    SELECT_INTEREST,
    SELECT_EMAIL,
    CODE_VERIFY,
    PASSWORD_SET,
    IMAGE_SET;

    companion object {
        fun ProfileCreateState.nextState(): ProfileCreateState {
            val currentIndex = this.ordinal
            Timber.w("testing nextState current:$this, currentIndex:$currentIndex")
            return if (currentIndex == ProfileCreateState.values().size - 1) {
                this
            } else {
                val nextIndex = currentIndex + 1
                ProfileCreateState.values()[nextIndex]
            }
        }

        fun ProfileCreateState.previousState(): ProfileCreateState {
            val currentIndex = this.ordinal
            Timber.w("testing previousState current:$this, currentIndex:$currentIndex")
            return if (currentIndex == 0) {
                this
            } else {
                val nextIndex = currentIndex - 1
                ProfileCreateState.values()[nextIndex]
            }
        }

        fun ProfileCreateState.isStartState(): Boolean {
            val currentIndex = this.ordinal
            return currentIndex == 0
        }

        fun ProfileCreateState.isLastState(): Boolean {
            val currentIndex = this.ordinal
            return currentIndex == ProfileCreateState.values().size - 1
        }

        fun ProfileCreateState.completeProgress(): Float {
            val current = this.ordinal + 1
            val total = ProfileCreateState.values().size
            val progressValue = current / total.toFloat()
            Timber.w("testing current:$current, total:$total, progressValue:$progressValue")
            return progressValue
        }
    }
}