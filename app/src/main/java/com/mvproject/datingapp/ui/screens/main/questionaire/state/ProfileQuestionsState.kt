/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.state

import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STEP_1

enum class ProfileQuestionsState {
    START,
    ABOUT,
    ORIENTATION,
    MARITAL_STATUS,
    CHILDREN,
    HEIGHT,
    ZODIAC,
    ALCOHOL,
    SMOKE,
    PSY_ORIENTATION,
    RELIGION,
    LANGUAGES,
    PETS,
    WORK,
    END;

    companion object {
        fun ProfileQuestionsState.nextState(): ProfileQuestionsState {
            val currentIndex = this.ordinal
            return if (currentIndex == ProfileQuestionsState.values().size - STEP_1) {
                this
            } else {
                val nextIndex = currentIndex + STEP_1
                ProfileQuestionsState.values()[nextIndex]
            }
        }

        fun ProfileQuestionsState.previousState(): ProfileQuestionsState {
            val currentIndex = this.ordinal
            return if (currentIndex == INT_ZERO) {
                this
            } else {
                val nextIndex = currentIndex - STEP_1
                ProfileQuestionsState.values()[nextIndex]
            }
        }

        fun ProfileQuestionsState.isStartState(): Boolean {
            val currentIndex = this.ordinal
            return currentIndex == INT_ZERO
        }

        fun ProfileQuestionsState.isLastState(): Boolean {
            val currentIndex = this.ordinal
            return currentIndex == ProfileQuestionsState.values().size - STEP_1
        }

        fun ProfileQuestionsState.completeProgress(): Float {
            val current = this.ordinal + STEP_1
            val total = ProfileQuestionsState.values().size
            return current / total.toFloat()
        }

        fun ProfileQuestionsState.completeContentProgress(): Float {
            if (this == START || this == END) {
                return 0f
            }
            val current = this.ordinal
            val total = ProfileQuestionsState.values().drop(STEP_1).size
            return current / total.toFloat()
        }
    }
}