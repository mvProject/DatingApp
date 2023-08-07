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

enum class FilterInterest(
    @StringRes override val title: Int
) : ProfileCharacteristic {
    DATE(
        title = R.string.filter_interest_date
    ),
    RELATION(
        title = R.string.filter_interest_relationship
    ),
    CHAT(
        title = R.string.filter_interest_just_chat
    ),
    LOVE(
        title = R.string.filter_interest_find_love
    );

    companion object {
        fun fromStringOrDefault(str: String?): List<FilterInterest> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterInterest.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}