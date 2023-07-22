/*
 * Create by Medvediev Viktor
 * last update: 20.07.23, 16:38
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit.state

import com.mvproject.datingapp.R
import com.mvproject.datingapp.utils.INT_ZERO

enum class EditProfileOption {
    INTEREST,
    LOCATION,
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
    WORK;


    companion object {
        fun EditProfileOption.stateTitle() = when (this) {
            INTEREST -> R.string.scr_auth_interest_select_title
            LOCATION -> R.string.scr_auth_location_select_title
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
        }

        fun EditProfileOption.stateLogo() = when (this) {
            INTEREST -> INT_ZERO
            LOCATION -> INT_ZERO
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
        }
    }
}