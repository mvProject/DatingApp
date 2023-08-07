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

enum class FilterLanguage(
    @StringRes override val title: Int,
) : ProfileCharacteristic {
    ENGLISH(
        title = R.string.filter_language_english
    ),
    UKRAINIAN(
        title = R.string.filter_language_ukrainian
    ),
    LATVIAN(
        title = R.string.filter_language_latvian
    ),
    GERMAN(
        title = R.string.filter_language_german
    ),
    FRENCH(
        title = R.string.filter_language_french
    ),
    SPANISH(
        title = R.string.filter_language_spanish
    ),
    ITALIAN(
        title = R.string.filter_language_italian
    ),
    POLISH(
        title = R.string.filter_language_polish
    ),
    TURKISH(
        title = R.string.filter_language_turkish
    ),
    CZECH(
        title = R.string.filter_language_czech
    ),
    PORTUGUESE(
        title = R.string.filter_language_portuguese
    ),
    DUTCH(
        title = R.string.filter_language_dutch
    ),
    CHINESE(
        title = R.string.filter_language_chinese
    ),
    JAPANESE(
        title = R.string.filter_language_japanese
    );

    companion object {
        private val defaultLanguageList = listOf(
            ENGLISH,
            UKRAINIAN,
            POLISH,
        )

        fun fromStringOrDefault(str: String?): List<FilterLanguage> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterLanguage.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}