/*
 * Create by Medvediev Viktor
 * last update: 08.06.23, 17:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire

import androidx.lifecycle.ViewModel
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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileQuestionViewModel @Inject constructor(

) : ViewModel() {
    private val _profileQuestionsDataState = MutableStateFlow(ProfileQuestionsDataState())
    val profileQuestionsDataState = _profileQuestionsDataState.asStateFlow()

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
                /*                val aboutInfo = action.data
                                if (aboutInfo.isNotEmpty()) {
                                    _profileQuestionsDataState.update {
                                        it.copy(profileAbout = action.data)
                                    }
                                }*/
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileAbout = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileOrientation -> {
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileOrientation = action.data)
                // _profileQuestionsDataState.update {
                //     it.copy(profileOrientation = action.data)
                // }
            }

            is ProfileQuestionsAction.UpdateProfileMarital -> {
                /*                _profileQuestionsDataState.update {
                                    it.copy(profileMarital = action.data)
                                }*/
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileMarital = action.data)
            }

            is ProfileQuestionsAction.UpdateProfileChildren -> {
                /*                _profileQuestionsDataState.update {
                                    it.copy(profileChildren = action.data)
                                }*/
                _profileQuestionsDataState.value = updatedState(
                    newState = profileQuestionsDataState.value.currentStep.nextState()
                ).copy(profileChildren = action.data)
            }
        }

        Timber.w("testing user info ${profileQuestionsDataState.value}")
    }

    private fun updatedState(newState: ProfileQuestionsState): ProfileQuestionsDataState {
        return profileQuestionsDataState.value.copy(
            currentStep = newState,
            currentStepProgress = newState.completeContentProgress()
        )

    }
}