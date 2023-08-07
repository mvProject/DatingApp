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

enum class FilterCharacter(
    @StringRes override val title: Int
) : ProfileCharacteristic {
    EXTRAVERT(
        title = R.string.filter_character_extrovert
    ),
    INTROVERT(
        title = R.string.filter_character_introvert
    ),
    BETWEEN(
        title = R.string.filter_character_somewhere_between
    );

    companion object {
        fun fromStringOrDefault(str: String?): List<FilterCharacter> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterCharacter.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}