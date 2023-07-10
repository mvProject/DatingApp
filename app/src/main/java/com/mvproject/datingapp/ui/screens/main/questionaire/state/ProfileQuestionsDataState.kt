/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.state

import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.completeContentProgress

data class ProfileQuestionsDataState(
    val currentStep: ProfileQuestionsState = ProfileQuestionsState.START,
    val currentStepProgress: Float = ProfileQuestionsState.START.completeContentProgress(),
    val isComplete: Boolean = false
)
