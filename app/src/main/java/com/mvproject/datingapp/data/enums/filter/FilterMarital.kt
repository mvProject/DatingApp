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

enum class FilterMarital(
    @StringRes override val title: Int
) : ProfileCharacteristic {
    FREE(
        title = R.string.filter_status_free
    ),
    IN_RELATION(
        title = R.string.filter_status_in_relationship
    ),
    OPEN_RELATION(
        title = R.string.filter_status_in_open_relationship
    );

    companion object {
        fun fromStringOrDefault(str: String?): List<FilterMarital> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterMarital.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}