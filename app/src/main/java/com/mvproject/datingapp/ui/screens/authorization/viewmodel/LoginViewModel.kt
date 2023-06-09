/*
 * Create by Medvediev Viktor
 * last update: 07.06.23, 14:17
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.viewmodel

import androidx.lifecycle.ViewModel
import com.mvproject.datingapp.ui.screens.authorization.actions.LoginDataAction
import com.mvproject.datingapp.ui.screens.authorization.state.LoginDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginDataState = MutableStateFlow(LoginDataState())
    val loginDataState = _loginDataState.asStateFlow()

    fun processAction(action: LoginDataAction) {
        when (action) {
            is LoginDataAction.UpdateLogin -> {
                _loginDataState.update {
                    it.copy(email = action.text)
                }
            }

            is LoginDataAction.UpdatePassword -> {
                _loginDataState.update {
                    it.copy(password = action.text)
                }
            }
        }

    }
}