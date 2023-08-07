/*
 * Create by Medvediev Viktor
 * last update: 20.07.23, 16:38
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating.state

import com.mvproject.datingapp.R

enum class FilterProfileOption {
    GENDER_INTEREST,
    AGE,
    DISTANCE,
    ORIENTATION,
    MARITAL_STATUS,
    INTEREST,
    CHILDREN,
    HEIGHT,
    ZODIAC,
    ALCOHOL,
    SMOKE,
    CHARACTER,
    RELIGION,
    LANGUAGES,
    PETS,
    NONE;

    val isValueSet get() = this != NONE

    companion object {
        fun FilterProfileOption.stateTitle() = when (this) {
            GENDER_INTEREST -> R.string.profile_filter_option_gender_interest
            AGE -> R.string.profile_filter_option_age
            DISTANCE -> R.string.profile_filter_option_distance
            ORIENTATION -> R.string.profile_filter_option_sexual_orientation
            MARITAL_STATUS -> R.string.profile_filter_option_marital_status
            INTEREST -> R.string.profile_filter_option_interest
            CHILDREN -> R.string.profile_filter_option_children
            HEIGHT -> R.string.profile_filter_option_height
            ZODIAC -> R.string.profile_filter_option_zodiac_sign
            ALCOHOL -> R.string.profile_filter_option_alcohol
            SMOKE -> R.string.profile_filter_option_smoking
            CHARACTER -> R.string.profile_filter_option_character
            RELIGION -> R.string.profile_filter_option_religion
            LANGUAGES -> R.string.profile_filter_option_languages
            PETS -> R.string.profile_filter_option_pets
            NONE -> R.string.option_not_set
        }
    }
}