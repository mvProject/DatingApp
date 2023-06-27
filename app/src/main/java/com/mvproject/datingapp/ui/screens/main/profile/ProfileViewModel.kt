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
import com.mvproject.datingapp.data.PreferenceRepository
import com.mvproject.datingapp.helper.FirebaseHelper
import com.mvproject.datingapp.helper.GoogleSignHelper
import com.mvproject.datingapp.ui.screens.main.profile.state.ProfileViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseHelper: FirebaseHelper,
    private val preferenceRepository: PreferenceRepository,
    private val googleSignHelper: GoogleSignHelper
) : ViewModel() {

    val profileUiState: StateFlow<ProfileViewState> = preferenceRepository
        .getUserLoggedState().map { isLogged ->
            if (isLogged)
                ProfileViewState.LoggedIn
            else ProfileViewState.NotLoggedIn
        }.stateIn(
            scope = viewModelScope,
            initialValue = ProfileViewState.Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )

    fun logoutProfile() {
        viewModelScope.launch {
            googleSignHelper.signOutGoogleAccount()
            preferenceRepository.setUserLoggedState(false)
        }
    }
}