/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.questionaire.ProfileQuestionScreen
import com.mvproject.datingapp.ui.screens.main.questionaire.ProfileQuestionViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600
import timber.log.Timber

fun NavController.navigateToProfileQuestions(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.ProfileQuestions.route, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
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
        Timber.w("testing NavigationHost ProfileQuestions")
        val profileQuestionViewModel = hiltViewModel<ProfileQuestionViewModel>()

        ProfileQuestionScreen(
            viewModel = profileQuestionViewModel,
            onNavigateNext = onNavigateNext
        )
    }
}