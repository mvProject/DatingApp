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

enum class FilterChildren(
    @StringRes override val title: Int,
) : ProfileCharacteristic {
    YES(
        title = R.string.filter_children_yes
    ),
    NO(
        title = R.string.filter_children_no
    ),
    WANT_HAVE(
        title = R.string.filter_children_wants_children
    ),
    NO_WANT_HAVE(
        title = R.string.filter_children_wants_no_children
    );

    companion object {
        fun fromStringOrDefault(str: String?): List<FilterChildren> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterChildren.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}