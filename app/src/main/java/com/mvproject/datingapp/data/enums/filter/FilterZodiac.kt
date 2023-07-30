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

enum class FilterZodiac(
    @StringRes override val title: Int,
) : ProfileCharacteristic {
    ARIES(
        title = R.string.filter_zodiac_aries,
    ),
    TAURUS(
        title = R.string.filter_zodiac_taurus,
    ),
    GEMINI(
        title = R.string.filter_zodiac_gemini,
    ),
    CANCER(
        title = R.string.filter_zodiac_cancer,
    ),
    LEO(
        title = R.string.filter_zodiac_leo,
    ),
    VIRGO(
        title = R.string.filter_zodiac_virgo,
    ),
    LIBRA(
        title = R.string.filter_zodiac_libra,
    ),
    SCORPIO(
        title = R.string.filter_zodiac_scorpio,
    ),
    SAGITTARIUS(
        title = R.string.filter_zodiac_sagittarius,
    ),
    CAPRICORN(
        title = R.string.filter_zodiac_capricorn,
    ),
    AQUARIUS(
        title = R.string.filter_zodiac_aquarius,
    ),
    PISCES(
        title = R.string.filter_zodiac_pisces,
    );

    companion object {
        fun fromStringOrDefault(str: String?): List<FilterZodiac> {
            if (str == null) return emptyList()
            return try {
                str.split(STRING_SEPARATOR)
                    .toList()
                    .map {
                        FilterZodiac.valueOf(it)
                    }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}