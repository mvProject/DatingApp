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
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.profile.view.state.ProfileDataState
import com.mvproject.datingapp.ui.screens.main.profile.view.state.ProfileViewState
import com.mvproject.datingapp.utils.calculatAgeMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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
                            profileInterest = user.interest,
                            activationStatus = activationState.status,
                            activationExpires = activationState.period
                        )
                    }
                }
        }
    }
}