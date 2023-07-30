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

enum class FilterReligion(
    @StringRes override val title: Int
) : ProfileCharacteristic {
    AGNOSTICISM(
        title = R.string.filter_religion_agnosticism
    ),
    ATHEISM(
        title = R.string.filter_religion_atheism
    ),
    BUDDHISM(
        title = R.string.filter_religion_buddhism
    ),
    CATHOLICISM(
        title = R.string.filter_religion_catholicism
    ),
    CHRISTIANITY(
        title = R.string.filter_religion_christianity
    ),
    HINDUISM(
        title = R.string.filter_religion_hinduism
    ),
    JAINISM(
        title = R.string.filter_religion_jainism
    ),
    JUDAISM(
        title = R.string.filter_religion_judaism
    ),
    MORMONISM(
        title = R.string.filter_religion_mormonism
    ),
    ISLAM(
        title = R.string.filter_religion_islam
    ),
    ZOROASTRIANISM(
        title = R.string.filter_religion_zoroastrianism
    ),
    SIKHISM(
        title = R.string.filter_religion_sikhism
    ),
    SPIRITUALISM(
        title = R.string.filter_religion_spiritualism
    ),
    OTHER(
        title = R.string.filter_religion_other
    );

    companion object {
        fun fromStringOrDefault(str: String?): List<FilterReligion> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterReligion.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}