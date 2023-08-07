/*
 * Create by Medvediev Viktor
 * last update: 30.07.23, 16:11
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums.filter

import androidx.annotation.StringRes
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileCharacteristic

enum class FilterSmoke(
    @StringRes override val title: Int
) : ProfileCharacteristic {
    YES(
        title = R.string.filter_smoke_yes
    ),
    NO(
        title = R.string.filter_smoke_no
    ),
    NOT_SET(
        title = R.string.title_not_set
    );

    val isValueSet get() = this != NOT_SET

    companion object {
        fun fromStringOrDefault(str: String?): FilterSmoke {
            if (str == null) return NOT_SET
            return try {
                FilterSmoke.valueOf(str)
            } catch (ex: Exception) {
                NOT_SET
            }
        }
    }
}