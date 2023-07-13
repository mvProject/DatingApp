/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 13:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.enums

import androidx.annotation.StringRes
import com.mvproject.datingapp.R
import com.mvproject.datingapp.utils.INT_ZERO

enum class ProfileZodiac(
    @StringRes val title: Int,
    @StringRes val date: Int
) {
    ZODIAC_ARIES(
        title = R.string.option_zodiac_aries,
        date = R.string.option_zodiac_aries_date,
    ),
    ZODIAC_TAURUS(
        title = R.string.option_zodiac_taurus,
        date = R.string.option_zodiac_taurus_date
    ),
    ZODIAC_GEMINI(
        title = R.string.option_zodiac_gemini,
        date = R.string.option_zodiac_gemini_date
    ),
    ZODIAC_CANCER(
        title = R.string.option_zodiac_cancer,
        date = R.string.option_zodiac_cancer_date
    ),
    ZODIAC_LEO(
        title = R.string.option_zodiac_leo,
        date = R.string.option_zodiac_leo_date
    ),
    ZODIAC_VIRGO(
        title = R.string.option_zodiac_virgo,
        date = R.string.option_zodiac_virgo_date
    ),
    ZODIAC_LIBRA(
        title = R.string.option_zodiac_libra,
        date = R.string.option_zodiac_libra_date
    ),
    ZODIAC_SCORPIO(
        title = R.string.option_zodiac_scorpio,
        date = R.string.option_zodiac_scorpio_date
    ),
    ZODIAC_SAGITTARIUS(
        title = R.string.option_zodiac_sagittarius,
        date = R.string.option_zodiac_sagittarius_date
    ),
    ZODIAC_CAPRICORN(
        title = R.string.option_zodiac_capricorn,
        date = R.string.option_zodiac_capricorn_date
    ),
    ZODIAC_AQUARIUS(
        title = R.string.option_zodiac_aquarius,
        date = R.string.option_zodiac_aquarius_date
    ),
    ZODIAC_PISCES(
        title = R.string.option_zodiac_pisces,
        date = R.string.option_zodiac_pisces_date
    ),
    ZODIAC_NOT_SET(
        title = R.string.option_not_set,
        date = INT_ZERO
    )
}