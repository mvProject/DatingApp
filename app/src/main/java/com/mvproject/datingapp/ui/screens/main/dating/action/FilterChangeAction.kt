/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating.action

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

sealed class FilterChangeAction {
    data class AgeFilter(val start: Int, val end: Int) : FilterChangeAction()
    data class HeightFilter(val start: Int, val end: Int) : FilterChangeAction()
    data class DistanceFilter(val data: Int) : FilterChangeAction()
    data class GenderFilter(val data: FilterGender) : FilterChangeAction()
    data class OrientationFilter(val data: FilterOrientation) : FilterChangeAction()
    data class MaritalFilter(val data: List<FilterMarital>) : FilterChangeAction()
    data class InterestFilter(val data: List<FilterInterest>) : FilterChangeAction()
    data class ChildrenFilter(val data: List<FilterChildren>) : FilterChangeAction()
    data class ZodiacFilter(val data: List<FilterZodiac>) : FilterChangeAction()
    data class AlcoholFilter(val data: List<FilterAlcohol>) : FilterChangeAction()
    data class SmokeFilter(val data: FilterSmoke) : FilterChangeAction()
    data class CharacterFilter(val data: List<FilterCharacter>) : FilterChangeAction()
    data class ReligionFilter(val data: List<FilterReligion>) : FilterChangeAction()
    data class LanguagesFilter(val data: List<FilterLanguage>) : FilterChangeAction()
    data class PetsFilter(val data: List<FilterPets>) : FilterChangeAction()
}