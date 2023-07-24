/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 13:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.data.model.UserActivation
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.profile.view.state.ProfileDataState
import com.mvproject.datingapp.ui.screens.main.profile.view.state.ProfileViewState
import com.mvproject.datingapp.utils.STEP_1
import com.mvproject.datingapp.utils.calculatAgeMillis
import com.mvproject.datingapp.utils.convertDateToReadableFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.days

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _profileUiState = MutableStateFlow<ProfileViewState>(ProfileViewState.Loading)
    val profileUiState = _profileUiState.asStateFlow()

    private val _profileState = MutableStateFlow(ProfileDataState())
    val profileState = _profileState.asStateFlow()

    init {
        viewModelScope.launch {
            val isLogged = preferenceRepository
                .getUserLoggedState()
            _profileUiState.value = if (isLogged)
                ProfileViewState.LoggedIn
            else ProfileViewState.NotLoggedIn
        }

        viewModelScope.launch {
            preferenceRepository.getUserFlow()
                .collect { user ->
                    val activationState = preferenceRepository.getActivationState()
                    _profileState.update {
                        it.copy(
                            profileName = user.name,
                            profileImage = user.profilePictureUrl,
                            profileAge = calculatAgeMillis(user.birthdate),
                            profileInterest = ProfileInterest.fromStringOrDefault(user.interest),
                            activationStatus = activationState.status,
                            activationExpires = activationState.period
                        )
                    }
                }
        }
    }

    fun activateFeatures() {
        val userActivation = if (profileState.value.activationStatus) {
            UserActivation()
        } else {
            val current = System.currentTimeMillis() + STEP_1.days.inWholeMilliseconds
            Timber.w("testing expires:$current, ${current.convertDateToReadableFormat()}")
            UserActivation(
                status = true,
                period = current
            )
        }

        viewModelScope.launch {
            preferenceRepository.setActivationState(userActivation)
            _profileState.update {
                it.copy(
                    activationStatus = userActivation.status,
                    activationExpires = userActivation.period
                )
            }
        }
    }
}