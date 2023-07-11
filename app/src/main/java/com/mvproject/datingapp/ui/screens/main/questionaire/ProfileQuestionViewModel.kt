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
import kotlinx.coroutines.flow.update
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
                    updateState(
                        newState = profileQuestionsDataState.value.currentStep.nextState()
                    )
                }
            }

            ProfileQuestionsAction.PrevStep -> {
                val isFirstState = profileQuestionsDataState.value.currentStep.isStartState()
                if (!isFirstState) {
                    updateState(
                        newState = profileQuestionsDataState.value.currentStep.previousState()
                    )
                }
            }

            is ProfileQuestionsAction.UpdateProfileAbout -> {
                val aboutInfo = action.data
                if (aboutInfo.isNotEmpty()) {
                    _profileQuestionsDataState.update {
                        it.copy(profileAbout = aboutInfo)
                    }
                }

            }
        }

        _profileQuestionsDataState.update {
            it.copy(currentStepProgress = profileQuestionsDataState.value.currentStep.completeContentProgress())
        }
    }

    private fun updateState(newState: ProfileQuestionsState) {
        _profileQuestionsDataState.update {
            it.copy(currentStep = newState)
        }
    }
}