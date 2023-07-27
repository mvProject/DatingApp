/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 13:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mvproject.datingapp.R
import com.mvproject.datingapp.utils.STRING_SEPARATOR

enum class ProfileLanguage(
    @StringRes val title: Int,
    @DrawableRes val logo: Int = R.drawable.ic_edit_languages
) {
    LANGUAGE_ENGLISH(
        title = R.string.option_language_english
    ),
    LANGUAGE_UKRAINIAN(
        title = R.string.option_language_ukrainian
    ),
    LANGUAGE_LATVIAN(
        title = R.string.option_language_latvian
    ),
    LANGUAGE_GERMAN(
        title = R.string.option_language_german
    ),
    LANGUAGE_FRENCH(
        title = R.string.option_language_french
    ),
    LANGUAGE_SPANISH(
        title = R.string.option_language_spanish
    ),
    LANGUAGE_ITALIAN(
        title = R.string.option_language_italian
    ),
    LANGUAGE_POLISH(
        title = R.string.option_language_polish
    ),
    LANGUAGE_TURKISH(
        title = R.string.option_language_turkish
    ),
    LANGUAGE_CZECH(
        title = R.string.option_language_czech
    ),
    LANGUAGE_PORTUGUESE(
        title = R.string.option_language_portuguese
    ),
    LANGUAGE_DUTCH(
        title = R.string.option_language_dutch
    ),
    LANGUAGE_CHINESE(
        title = R.string.option_language_chinese
    ),
    LANGUAGE_JAPANESE(
        title = R.string.option_language_japanese
    );

    companion object {
        val defaultLanguageList = listOf(
            LANGUAGE_ENGLISH,
            LANGUAGE_UKRAINIAN,
            LANGUAGE_POLISH,
        )

        fun fromStringOrDefault(str: String?): List<ProfileLanguage> {
            if (str == null) return defaultLanguageList
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        ProfileLanguage.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}