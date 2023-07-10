/*
 * Create by Medvediev Viktor
 * last update: 19.06.23, 17:23
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.model.User
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.data.repository.StorageRepository
import com.mvproject.datingapp.dummy.DUMMY_CODE
import com.mvproject.datingapp.helper.FirebaseHelper
import com.mvproject.datingapp.helper.TimerHelper
import com.mvproject.datingapp.ui.screens.authorization.signup.actions.SignUpAction
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpProfileDataState
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState.Companion.completeProgress
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState.Companion.isLastState
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState.Companion.isStartState
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState.Companion.nextState
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState.Companion.previousState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseHelper: FirebaseHelper,
    private val storageRepository: StorageRepository,
    private val preferenceRepository: PreferenceRepository,
    private val timerHelper: TimerHelper
) : ViewModel() {

    private val _profileDataState = MutableStateFlow(SignUpProfileDataState())
    val profileDataState = _profileDataState.asStateFlow()

    val timerState = timerHelper.timerState

    fun launchTimer() {
        timerHelper.launchTimer()
    }

    fun processAction(action: SignUpAction) {
        when (action) {
            SignUpAction.NextStep -> {
                val isLastState = profileDataState.value.currentStep.isLastState()
                if (!isLastState) {
                    updateState(
                        newState = profileDataState.value.currentStep.nextState()
                    )
                }
            }

            SignUpAction.PrevStep -> {
                val isFirstState = profileDataState.value.currentStep.isStartState()
                if (!isFirstState) {
                    updateState(
                        newState = profileDataState.value.currentStep.previousState()
                    )
                }
            }

            is SignUpAction.UpdateEmail -> {
                _profileDataState.update {
                    it.copy(
                        email = action.text,
                        code = DUMMY_CODE
                    )
                }
            }

            is SignUpAction.UpdatePassword -> {
                _profileDataState.update {
                    it.copy(password = action.text)
                }
            }

            is SignUpAction.UpdateBirthdate -> {
                _profileDataState.update {
                    it.copy(birthdate = action.date)
                }
            }

            is SignUpAction.UpdateGender -> {
                _profileDataState.update {
                    it.copy(gender = action.gender)
                }
            }

            is SignUpAction.UpdateName -> {
                _profileDataState.update {
                    it.copy(name = action.text)
                }
            }

            is SignUpAction.UpdateInterest -> {
                _profileDataState.update {
                    it.copy(interest = action.interest)
                }
            }

            is SignUpAction.UpdateLocation -> {
                _profileDataState.update {
                    it.copy(location = action.location)
                }
            }

            is SignUpAction.UpdatePhotos -> {
                viewModelScope.launch {
                    val photos = storageRepository.setUserPhotos(
                        username = profileDataState.value.email,
                        source = action.uris
                    )

                    _profileDataState.update {
                        it.copy(images = photos)
                    }

                    registerUser()

                    _profileDataState.update {
                        it.copy(isComplete = true)
                    }
                }
            }

            SignUpAction.ResendCode -> {
                timerHelper.launchTimer()
            }

        }

        _profileDataState.update {
            it.copy(currentStepProgress = profileDataState.value.currentStep.completeProgress())
        }
    }

    private fun updateState(newState: SignUpState) {
        _profileDataState.update {
            it.copy(currentStep = newState)
        }
    }

    private fun dataToUser(): User {
        val data = profileDataState.value
        Timber.w("testing dataToUser")
        return User(
            name = data.name,
            birthdate = data.birthdate,
            email = data.email,
            password = data.password,
            interest = data.interest.name,
            gender = data.gender.name,
            location = data.location.toString(),
            profilePictureUrl = data.images.first(),
            photos = data.images
        )
    }

    private fun registerUser() {
        viewModelScope.launch {
            val user = dataToUser()
            preferenceRepository.saveUser(user)
            preferenceRepository.setUserLoggedState(true)
            Timber.w("testing registerUser")
        }
    }
}