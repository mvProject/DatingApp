/*
 * Create by Medvediev Viktor
 * last update: 29.07.23, 18:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.data.enums.filter.FilterAlcohol
import com.mvproject.datingapp.data.enums.filter.FilterCharacter
import com.mvproject.datingapp.data.enums.filter.FilterChildren
import com.mvproject.datingapp.data.enums.filter.FilterGender
import com.mvproject.datingapp.data.enums.filter.FilterInterest
import com.mvproject.datingapp.data.enums.filter.FilterLanguage
import com.mvproject.datingapp.data.enums.filter.FilterMarital
import com.mvproject.datingapp.data.enums.filter.FilterOrientation
import com.mvproject.datingapp.data.enums.filter.FilterPets
import com.mvproject.datingapp.data.enums.filter.FilterReligion
import com.mvproject.datingapp.data.enums.filter.FilterSmoke
import com.mvproject.datingapp.data.enums.filter.FilterZodiac
import com.mvproject.datingapp.utils.DEFAULT_FILTER_AGE_MAX
import com.mvproject.datingapp.utils.DEFAULT_FILTER_AGE_MIN
import com.mvproject.datingapp.utils.DEFAULT_FILTER_DISTANCE
import com.mvproject.datingapp.utils.DEFAULT_FILTER_HEIGHT_MAX
import com.mvproject.datingapp.utils.DEFAULT_FILTER_HEIGHT_MIN

data class FilterData(
    val startAge: Int = DEFAULT_FILTER_AGE_MIN,
    val endAge: Int = DEFAULT_FILTER_AGE_MAX,
    val startHeight: Int = DEFAULT_FILTER_HEIGHT_MIN,
    val endHeight: Int = DEFAULT_FILTER_HEIGHT_MAX,
    val isHeightSet: Boolean = false,
    val distance: Int = DEFAULT_FILTER_DISTANCE,
    val filterGender: FilterGender = FilterGender.MEN,
    val filterOrientation: FilterOrientation = FilterOrientation.NOT_SET,
    val filterMaritals: List<FilterMarital> = listOf(),
    val filterInterests: List<FilterInterest> = listOf(),
    val filterChildrens: List<FilterChildren> = listOf(),
    val filterZodiacs: List<FilterZodiac> = listOf(),
    val filterAlcohols: List<FilterAlcohol> = listOf(),
    val filterSmoke: FilterSmoke = FilterSmoke.NOT_SET,
    val filterCharacter: List<FilterCharacter> = listOf(),
    val filterReligions: List<FilterReligion> = listOf(),
    val filterLanguages: List<FilterLanguage> = listOf(),
    val filterPets: List<FilterPets> = listOf()
)