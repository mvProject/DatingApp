/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.state

import com.mvproject.datingapp.R
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

        fun ProfileQuestionsState.stateTitle() = when (this) {
            START -> R.string.questionnaire_start_title
            ABOUT -> R.string.questionnaire_about_title
            ORIENTATION -> R.string.questionnaire_orientation_title
            MARITAL_STATUS -> R.string.questionnaire_marital_status_title
            CHILDREN -> R.string.questionnaire_children_title
            HEIGHT -> R.string.questionnaire_height_title
            ZODIAC -> R.string.questionnaire_zodiac_title
            ALCOHOL -> R.string.questionnaire_alcohol_title
            SMOKE -> R.string.questionnaire_smoke_title
            PSY_ORIENTATION -> R.string.questionnaire_vert_title
            RELIGION -> R.string.questionnaire_religion_title
            LANGUAGES -> R.string.questionnaire_languages_title
            PETS -> R.string.questionnaire_pets_title
            WORK -> R.string.questionnaire_work_title
            END -> R.string.questionnaire_end_title
        }

        fun ProfileQuestionsState.stateLogo() = when (this) {
            START -> R.drawable.questions_start
            ABOUT -> R.drawable.questions_about
            ORIENTATION -> R.drawable.questions_orientation
            MARITAL_STATUS -> R.drawable.questions_marital_status
            CHILDREN -> R.drawable.questions_children
            HEIGHT -> R.drawable.questions_height
            ZODIAC -> R.drawable.questions_zodiac
            ALCOHOL -> R.drawable.questions_alcohol
            SMOKE -> R.drawable.questions_smoke
            PSY_ORIENTATION -> R.drawable.questions_vert
            RELIGION -> R.drawable.questions_religion
            LANGUAGES -> R.drawable.questions_languages
            PETS -> R.drawable.questions_pets
            WORK -> R.drawable.questions_work
            END -> R.drawable.questions_end
        }

        fun ProfileQuestionsState.stateNavLogo() = when (this) {
            START -> R.drawable.ic_close
            else -> R.drawable.ic_navigate_back
        }
    }
}