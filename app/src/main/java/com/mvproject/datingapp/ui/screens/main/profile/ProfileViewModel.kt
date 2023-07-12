/*
 * Create by Medvediev Viktor
 * last update: 22.06.23, 11:27
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.helper.FirebaseHelper
import com.mvproject.datingapp.helper.GoogleSignHelper
import com.mvproject.datingapp.ui.screens.main.profile.state.ProfileViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseHelper: FirebaseHelper,
    private val preferenceRepository: PreferenceRepository,
    private val googleSignHelper: GoogleSignHelper
) : ViewModel() {

    private val _profileUiState = MutableStateFlow<ProfileViewState>(ProfileViewState.Loading)
    val profileUiState = _profileUiState.asStateFlow()

    private val _profileState = MutableStateFlow(UserState())
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
            val user = preferenceRepository.getUser()
            _profileState.update {
                it.copy(
                    profileName = user.name,
                    profileImage = user.profilePictureUrl
                )
            }
        }
    }

    fun logoutProfile() {
        viewModelScope.launch {
            googleSignHelper.signOutGoogleAccount()
            preferenceRepository.setUserLoggedState(false)
            _profileUiState.value = ProfileViewState.NotLoggedIn
        }
    }

    data class UserState(
        val profileName: String = "",
        val profileImage: String = "",
    )
}