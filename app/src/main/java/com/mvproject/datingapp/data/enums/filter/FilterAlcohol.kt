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
import com.mvproject.datingapp.utils.STRING_SEPARATOR

enum class FilterAlcohol(
    @StringRes override val title: Int,
) : ProfileCharacteristic {
    IN_COMPANY(
        title = R.string.filter_alcohol_in_company
    ),
    NEVER(
        title = R.string.filter_alcohol_never
    ),
    OFTEN(
        title = R.string.filter_alcohol_often
    ),
    ABSTAIN(
        title = R.string.filter_alcohol_abstain
    );

    companion object {
        fun fromStringOrDefault(str: String?): List<FilterAlcohol> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterAlcohol.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}