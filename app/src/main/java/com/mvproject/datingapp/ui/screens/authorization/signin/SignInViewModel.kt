/*
 * Create by Medvediev Viktor
 * last update: 22.06.23, 17:22
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.PreferenceRepository
import com.mvproject.datingapp.helper.FirebaseHelper
import com.mvproject.datingapp.helper.GoogleSignHelper
import com.mvproject.datingapp.ui.screens.authorization.signin.action.SignInAction
import com.mvproject.datingapp.ui.screens.authorization.signin.state.SignInViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseHelper: FirebaseHelper,
    private val preferenceRepository: PreferenceRepository,
    private val googleSignHelper: GoogleSignHelper

) : ViewModel() {

    val googleSignIntent = googleSignHelper.googleSignClient.signInIntent

    val signInUiState: StateFlow<SignInViewState> = preferenceRepository
        .getUserLoggedState().map { isLogged ->
            if (isLogged)
                SignInViewState.LoggedIn
            else SignInViewState.NotLoggedIn
        }.stateIn(
            scope = viewModelScope,
            initialValue = SignInViewState.Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )

    fun processSignInAction(action: SignInAction) {
        when (action) {
            is SignInAction.SignWithCredentialIn -> {
                signWithCredentials(action.login, action.password)
            }

            is SignInAction.SignWithFacebookIn -> {
                // todo
            }

            is SignInAction.SignWithGoogleIn -> {
                signWithGoogle(googleToken = action.token)
            }
        }
    }

    private fun signWithGoogle(googleToken: String) {
        viewModelScope.launch {
            val user = googleSignHelper.signInWithGoogle(googleToken)
            if (user != null) {
                preferenceRepository.setUserLoggedState(true)
            }
        }
    }


    private fun signWithCredentials(login: String, password: String) {
        // todo dummy
        viewModelScope.launch {
            preferenceRepository.setUserLoggedState(true)
        }
    }
}
