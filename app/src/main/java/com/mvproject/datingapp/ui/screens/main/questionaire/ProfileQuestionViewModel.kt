/*
 * Create by Medvediev Viktor
 * last update: 08.06.23, 17:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.datingapp.data.repository.PreferenceRepository
import com.mvproject.datingapp.ui.screens.main.questionaire.action.ProfileQuestionsAction
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsDataState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.completeContentProgress
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.isLastState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.isStartState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.nextState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.previousState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileQuestionViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
) : ViewModel() {
    private val _profileQuestionsDataState = MutableStateFlow(ProfileQuestionsDataState())
    val profileQuestionsDataState = _profileQuestionsDataState.asStateFlow()

    init {
        Timber.w("testing ProfileQuestionViewModel Init")
    }

    fun processAction(action: ProfileQuestionsAction) {
        when (action) {
            ProfileQuestionsAction.NextStep -> {
                val isLastState = profileQuestionsDataState.value.currentStep.isLastState()
                if (!isLastState) {
                    _profileQuestionsDataState.value = updatedState(
                        newState = profileQuestionsDataState.value.currentStep.nextState()
                    )
                }
            }

            ProfileQuestionsAction.PrevStep -> {
                val isFirstState = profileQuestionsDataState.value.currentStep.isStartState()
                if (!isFirstState) {
                    _profileQuestionsDataState.value = updatedState(
                        newState = profileQuestionsDataState.value.currentStep.previousState()
                    )
                }
            }

            is ProfileQuestionsAction.UpdateProfileAbout -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileAbout = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileOrientation -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileOrientation = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileMarital -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileMarital = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileChildren -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileChildren = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileHeight -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(userHeight = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileZodiac -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileZodiac = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileAlcohol -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileAlcohol = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileSmoke -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileSmoke = action.data)
            }

            is ProfileQuestionsAction.UpdateProfilePsyOrientation -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profilePsyOrientation = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileReligion -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileReligion = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileLanguages -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileLanguages = action.data)
            }

            is ProfileQuestionsAction.UpdateProfilePets -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profilePets = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileWork -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(userWork = action.data)
            }

            is ProfileQuestionsAction.SaveProfileInfo -> {
                viewModelScope.launch {
                    val user = preferenceRepository.getUser()

                    val updatedUser = user.copy(
                        profileAbout = profileQuestionsDataState.value.profileAbout,
                        profileOrientation = profileQuestionsDataState.value.profileOrientation,
                        profileMarital = profileQuestionsDataState.value.profileMarital,
                        profileChildren = profileQuestionsDataState.value.profileChildren,
                        profileHeight = profileQuestionsDataState.value.userHeight,
                        profileZodiac = profileQuestionsDataState.value.profileZodiac,
                        profileAlcohol = profileQuestionsDataState.value.profileAlcohol,
                        profileSmoke = profileQuestionsDataState.value.profileSmoke,
                        profilePsyOrientation = profileQuestionsDataState.value.profilePsyOrientation,
                        profileReligion = profileQuestionsDataState.value.profileReligion,
                        profileLanguages = profileQuestionsDataState.value.profileLanguages,
                        profilePets = profileQuestionsDataState.value.profilePets,
                        profileWork = profileQuestionsDataState.value.userWork,
                    )

                    preferenceRepository.saveUser(user = updatedUser)

                    _profileQuestionsDataState.update {
                        it.copy(isComplete = true)
                    }
                }
            }
        }
    }

    private fun updatedState(newState: ProfileQuestionsState): ProfileQuestionsDataState {
        return profileQuestionsDataState.value.copy(
            currentStep = newState,
            currentStepProgress = newState.completeContentProgress()
        )
    }

    override fun onCleared() {
        super.onCleared()
        Timber.w("testing ProfileQuestionViewModel onCleared")
    }
}