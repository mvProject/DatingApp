/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:01
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.enums.filter.FilterOrientation
import com.mvproject.datingapp.data.enums.filter.FilterSmoke
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.dating.action.FilterChangeAction
import com.mvproject.datingapp.ui.screens.main.dating.action.FilterCleanAction
import com.mvproject.datingapp.ui.screens.main.dating.state.DatingFilterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatingFilterViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _datingFilterState = MutableStateFlow(DatingFilterState())
    val datingFilterState = _datingFilterState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceRepository.getDatingFilters().collect { data ->
                _datingFilterState.update {
                    it.copy(filterData = data)
                }
            }
        }
    }

    fun applyFilters() {
        viewModelScope.launch {
            preferenceRepository.saveDatingFilters(data = datingFilterState.value.filterData)
        }
    }

    fun processChangeAction(action: FilterChangeAction) {
        val updated = when (action) {
            is FilterChangeAction.AlcoholFilter -> {
                datingFilterState.value.filterData.copy(filterAlcohols = action.data)
            }

            is FilterChangeAction.CharacterFilter -> {
                datingFilterState.value.filterData.copy(filterCharacter = action.data)
            }

            is FilterChangeAction.ChildrenFilter -> {
                datingFilterState.value.filterData.copy(filterChildrens = action.data)
            }

            is FilterChangeAction.HeightFilter -> {
                datingFilterState.value.filterData.copy(
                    startHeight = action.start,
                    endHeight = action.end,
                    isHeightSet = true
                )
            }

            is FilterChangeAction.InterestFilter -> {
                datingFilterState.value.filterData.copy(filterInterests = action.data)
            }

            is FilterChangeAction.LanguagesFilter -> {
                datingFilterState.value.filterData.copy(filterLanguages = action.data)
            }

            is FilterChangeAction.DistanceFilter -> {
                datingFilterState.value.filterData.copy(distance = action.data)
            }

            is FilterChangeAction.MaritalFilter -> {
                datingFilterState.value.filterData.copy(filterMaritals = action.data)
            }

            is FilterChangeAction.PetsFilter -> {
                datingFilterState.value.filterData.copy(filterPets = action.data)
            }

            is FilterChangeAction.AgeFilter -> {
                datingFilterState.value.filterData.copy(
                    startAge = action.start,
                    endAge = action.end
                )
            }

            is FilterChangeAction.ReligionFilter -> {
                datingFilterState.value.filterData.copy(filterReligions = action.data)
            }

            is FilterChangeAction.SmokeFilter -> {
                datingFilterState.value.filterData.copy(filterSmoke = action.data)
            }

            is FilterChangeAction.ZodiacFilter -> {
                datingFilterState.value.filterData.copy(filterZodiacs = action.data)
            }

            is FilterChangeAction.GenderFilter -> {
                datingFilterState.value.filterData.copy(filterGender = action.data)
            }

            is FilterChangeAction.OrientationFilter -> {
                datingFilterState.value.filterData.copy(filterOrientation = action.data)
            }
        }
        _datingFilterState.update {
            it.copy(filterData = updated)
        }
    }

    fun processCleanAction(action: FilterCleanAction) {
        val updated = when (action) {
            FilterCleanAction.CleanAll -> {
                val default = DatingFilterState().filterData
                val current = datingFilterState.value.filterData
                default.copy(
                    startAge = current.startAge,
                    endAge = current.endAge,
                    distance = current.distance,
                    filterGender = current.filterGender
                )
            }

            FilterCleanAction.CleanOrientation -> {
                datingFilterState.value.filterData.copy(filterOrientation = FilterOrientation.NOT_SET)
            }

            FilterCleanAction.CleanMarital -> {
                datingFilterState.value.filterData.copy(filterMaritals = emptyList())
            }

            FilterCleanAction.CleanInterest -> {
                datingFilterState.value.filterData.copy(filterInterests = emptyList())
            }

            FilterCleanAction.CleanChildren -> {
                datingFilterState.value.filterData.copy(filterChildrens = emptyList())
            }

            FilterCleanAction.CleanHeight -> {
                val default = DatingFilterState().filterData
                datingFilterState.value.filterData.copy(
                    startHeight = default.startHeight,
                    endHeight = default.endHeight,
                    isHeightSet = default.isHeightSet,
                )
            }

            FilterCleanAction.CleanZodiac -> {
                datingFilterState.value.filterData.copy(filterZodiacs = emptyList())
            }

            FilterCleanAction.CleanAlcohol -> {
                datingFilterState.value.filterData.copy(filterAlcohols = emptyList())
            }

            FilterCleanAction.CleanSmoke -> {
                datingFilterState.value.filterData.copy(filterSmoke = FilterSmoke.NOT_SET)
            }

            FilterCleanAction.CleanCharacter -> {
                datingFilterState.value.filterData.copy(filterCharacter = emptyList())
            }

            FilterCleanAction.CleanReligion -> {
                datingFilterState.value.filterData.copy(filterReligions = emptyList())
            }

            FilterCleanAction.CleanLanguages -> {
                datingFilterState.value.filterData.copy(filterPets = emptyList())
            }

            FilterCleanAction.CleanPets -> {
                datingFilterState.value.filterData.copy(filterPets = emptyList())
            }
        }
        _datingFilterState.update {
            it.copy(filterData = updated)
        }
    }
}