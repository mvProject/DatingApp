/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.questionaire.ProfileQuestionScreen
import com.mvproject.datingapp.ui.screens.main.questionaire.ProfileQuestionViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToProfileQuestionsClearStack() {
    this.popBackStack()
    this.navigate(AppRoutes.ProfileQuestions.route) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.profileQuestionsScreen(
    onNavigateNext: () -> Unit,
) {
    composable(
        route = AppRoutes.ProfileQuestions.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val profileQuestionViewModel = hiltViewModel<ProfileQuestionViewModel>()

        ProfileQuestionScreen(
            viewModel = profileQuestionViewModel,
            onNavigateNext = onNavigateNext
        )
    }
}