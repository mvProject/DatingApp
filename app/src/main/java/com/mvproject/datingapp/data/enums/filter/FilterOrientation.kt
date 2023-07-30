/*
 * Create by Medvediev Viktor
 * last update: 30.07.23, 16:09
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums.filter

import androidx.annotation.StringRes
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileCharacteristic

enum class FilterOrientation(
    @StringRes override val title: Int
) : ProfileCharacteristic {
    HETERO(
        title = R.string.filter_orientation_hetero
    ),
    WITHOUT_PREJUDICE(
        title = R.string.filter_orientation_without_prejudice
    ),
    BISEXUAL(
        title = R.string.filter_orientation_bisexual
    ),
    GAY_LESBIAN(
        title = R.string.filter_orientation_gay_lesbian
    ),
    NOT_SET(
        title = R.string.title_not_set
    );

    val isValueSet get() = this != NOT_SET

    companion object {
        fun fromStringOrDefault(str: String?): FilterOrientation {
            if (str == null) return NOT_SET
            return try {
                FilterOrientation.valueOf(str)
            } catch (ex: Exception) {
                NOT_SET
            }
        }
    }
}