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
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.helper.GoogleSignHelper
import com.mvproject.datingapp.ui.screens.authorization.signin.action.SignInAction
import com.mvproject.datingapp.ui.screens.authorization.signin.state.SignInViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val googleSignHelper: GoogleSignHelper
) : ViewModel() {

    private val _signInUiState = MutableStateFlow<SignInViewState>(SignInViewState.Loading)
    val signInUiState = _signInUiState.asStateFlow()

    val googleSignIntent = googleSignHelper.googleSignClient.signInIntent

    init {
        viewModelScope.launch {
            val isLogged = preferenceRepository.getUserLoggedState()
            Timber.w("testing isLogged:$isLogged")
            _signInUiState.value = if (isLogged)
                SignInViewState.LoggedIn
            else SignInViewState.NotLoggedIn
        }
    }

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
                setUserLogged()
            }
        }
    }


    private fun signWithCredentials(login: String, password: String) {
        // todo dummy
        setUserLogged()
    }

    private fun setUserLogged() {
        viewModelScope.launch {
            preferenceRepository.setUserLoggedState(true)
            _signInUiState.value = SignInViewState.LoggedIn
        }
    }
}
