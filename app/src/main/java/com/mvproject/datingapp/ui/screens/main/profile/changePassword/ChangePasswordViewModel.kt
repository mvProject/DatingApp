/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 19:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.changePassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.profile.changePassword.action.ChangePasswordAction
import com.mvproject.datingapp.ui.screens.main.profile.changePassword.state.ChangePasswordState
import com.mvproject.datingapp.utils.DELAY_1000
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _profileState = MutableStateFlow(ChangePasswordState())
    val profileState = _profileState.asStateFlow()

    init {
        viewModelScope.launch {
            val user = preferenceRepository.getUser()

            _profileState.update {
                it.copy(profilePassword = user.password)
            }
        }
    }

    fun processAction(action: ChangePasswordAction) {
        when (action) {
            ChangePasswordAction.SendPasswordComplete -> {
                _profileState.update {
                    it.copy(isPasswordWasChange = consumed)
                }
            }

            is ChangePasswordAction.UpdatePassword -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _profileState.update {
                        it.copy(isLoading = true)
                    }
                    delay(DELAY_1000)
                    // todo send request

                    _profileState.update {
                        it.copy(
                            isPasswordWasChange = triggered,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}