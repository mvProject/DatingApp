/*
 * Create by Medvediev Viktor
 * last update: 27.06.23, 13:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state

enum class RestoreAccessState {
    EMAIL_REQUEST,
    CODE_VERIFY,
    PASSWORD_SET;

    companion object {
        fun RestoreAccessState.nextState(): RestoreAccessState {
            val currentIndex = this.ordinal
            return if (currentIndex == RestoreAccessState.values().size - 1) {
                this
            } else {
                val nextIndex = currentIndex + 1
                RestoreAccessState.values()[nextIndex]
            }
        }

        fun RestoreAccessState.previousState(): RestoreAccessState {
            val currentIndex = this.ordinal
            return if (currentIndex == 0) {
                this
            } else {
                val nextIndex = currentIndex - 1
                RestoreAccessState.values()[nextIndex]
            }
        }

        fun RestoreAccessState.isStartState(): Boolean {
            val currentIndex = this.ordinal
            return currentIndex == 0
        }

        fun RestoreAccessState.isLastState(): Boolean {
            val currentIndex = this.ordinal
            return currentIndex == RestoreAccessState.values().size - 1
        }
    }
}