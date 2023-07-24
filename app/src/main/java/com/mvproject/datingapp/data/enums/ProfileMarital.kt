/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 13:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums

import androidx.annotation.StringRes
import com.mvproject.datingapp.R

enum class ProfileMarital(
    @StringRes val title: Int
) {
    MARITAL_FREE(
        title = R.string.option_marital_free
    ),
    MARITAL_IN_RELATION(
        title = R.string.option_marital_in_relationship
    ),
    MARITAL_COMPLICATED(
        title = R.string.option_marital_complicated
    ),
    MARITAL_OPEN_RELATION(
        title = R.string.option_marital_open_relationship
    ),
    MARITAL_NOT_SET(
        title = R.string.option_not_set
    );

    companion object {
        fun fromStringOrDefault(str: String?): ProfileMarital {
            if (str == null) return MARITAL_COMPLICATED
            return try {
                ProfileMarital.valueOf(str)
            } catch (ex: Exception) {
                MARITAL_COMPLICATED
            }
        }
    }
}