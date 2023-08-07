/*
 * Create by Medvediev Viktor
 * last update: 30.07.23, 16:22
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums.filter

import androidx.annotation.StringRes
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileCharacteristic

enum class FilterGender(
    @StringRes override val title: Int
) : ProfileCharacteristic {
    MEN(
        title = R.string.filter_gender_man
    ),
    WOMEN(
        title = R.string.filter_gender_woman
    ),
    ALL(
        title = R.string.filter_gender_all
    );

    companion object {
        fun fromStringOrDefault(str: String?): FilterGender {
            if (str == null) return MEN
            return try {
                FilterGender.valueOf(str)
            } catch (ex: Exception) {
                MEN
            }
        }
    }
}