/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 13:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.activation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.ActivationData
import com.mvproject.datingapp.data.model.UserActivation
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.profile.activation.action.ActivationAction
import com.mvproject.datingapp.ui.screens.main.profile.activation.state.ActivationDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import javax.inject.Inject

@HiltViewModel
class ActivationViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _activationDataState = MutableStateFlow(ActivationDataState())
    val activationDataState = _activationDataState.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceRepository.getUserFlow()
                .collect { user ->
                    val activationState = preferenceRepository.getActivationState()
                    val currentSelected =
                        ActivationData.activationPlans.first { it.planType == activationState.type }
                    _activationDataState.update {
                        it.copy(
                            activationStatus = activationState.status,
                            activationExpires = activationState.period,
                            selectedPlan = currentSelected
                        )
                    }
                }
        }
    }

    fun processAction(action: ActivationAction) {
        when (action) {
            ActivationAction.ActivatePlan -> {
                val userActivation = if (activationDataState.value.activationStatus) {
                    UserActivation()
                } else {
                    val init = Clock.System.now()
                    val duration = init.plus(
                        activationDataState.value.selectedPlan.monthDuration,
                        DateTimeUnit.MONTH,
                        TimeZone.currentSystemDefault()
                    )
                    val current = duration.toEpochMilliseconds()
                    UserActivation(
                        status = true,
                        period = current,
                        type = activationDataState.value.selectedPlan.planType
                    )
                }

                viewModelScope.launch {
                    preferenceRepository.setActivationState(userActivation)
                    _activationDataState.update {
                        it.copy(
                            activationStatus = userActivation.status,
                            activationExpires = userActivation.period,
                        )
                    }
                }
            }

            is ActivationAction.SelectPlan -> {
                _activationDataState.update {
                    it.copy(
                        selectedPlan = action.data
                    )
                }
            }
        }
    }
}