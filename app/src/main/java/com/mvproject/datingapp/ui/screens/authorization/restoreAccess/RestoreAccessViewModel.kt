/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.restoreAccess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.dummy.DUMMY_CODE
import com.mvproject.datingapp.helper.TimerHelper
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.action.RestoreAccessAction
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessDataState
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessState
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessState.Companion.isLastState
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessState.Companion.isStartState
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessState.Companion.nextState
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state.RestoreAccessState.Companion.previousState
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
class RestoreAccessViewModel @Inject constructor(
    private val timerHelper: TimerHelper
) : ViewModel() {

    private val _restoreAccessDataState = MutableStateFlow(RestoreAccessDataState())
    val restoreAccessDataState = _restoreAccessDataState.asStateFlow()

    val timerState = timerHelper.timerState

    fun launchTimer() {
        timerHelper.launchTimer()
    }

    fun processAction(action: RestoreAccessAction) {
        when (action) {
            is RestoreAccessAction.UpdateEmail -> {
                _restoreAccessDataState.update {
                    it.copy(email = action.text)
                }
            }

            is RestoreAccessAction.UpdatePassword -> {
                _restoreAccessDataState.update {
                    it.copy(password = action.text)
                }
            }

            is RestoreAccessAction.SendCode -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _restoreAccessDataState.update {
                        it.copy(
                            isLoading = true,
                            code = DUMMY_CODE
                        )
                    }
                    delay(DELAY_1000)
                    // todo send request

                    _restoreAccessDataState.update {
                        it.copy(
                            isCodeWasSend = triggered,
                            isLoading = false
                        )
                    }
                }
            }

            RestoreAccessAction.SendCodeComplete -> {
                _restoreAccessDataState.update {
                    it.copy(isCodeWasSend = consumed)
                }
            }

            is RestoreAccessAction.SendPassword -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _restoreAccessDataState.update {
                        it.copy(isLoading = true)
                    }
                    delay(DELAY_1000)
                    // todo send request

                    _restoreAccessDataState.update {
                        it.copy(
                            isPasswordWasChange = triggered,
                            isLoading = false
                        )
                    }
                }
            }

            RestoreAccessAction.SendPasswordComplete -> {
                _restoreAccessDataState.update {
                    it.copy(isPasswordWasChange = consumed)
                }
            }

            RestoreAccessAction.NextStep -> {
                val isLastState = restoreAccessDataState.value.currentStep.isLastState()
                if (!isLastState) {
                    updateState(
                        newState = restoreAccessDataState.value.currentStep.nextState()
                    )
                }
            }

            RestoreAccessAction.PrevStep -> {
                val isFirstState = restoreAccessDataState.value.currentStep.isStartState()
                if (!isFirstState) {
                    updateState(
                        newState = restoreAccessDataState.value.currentStep.previousState()
                    )
                }
            }

            RestoreAccessAction.ResendCode -> {
                timerHelper.launchTimer()
            }
        }
    }

    private fun updateState(newState: RestoreAccessState) {
        _restoreAccessDataState.update {
            it.copy(currentStep = newState)
        }
    }
}