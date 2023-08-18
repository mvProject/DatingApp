/*
 * Create by Medvediev Viktor
 * last update: 27.06.23, 14:42
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.restoreAccess.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.RestoreAccessScreen
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.RestoreAccessViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToForgot(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.RestoreAccess.route, navOptions)
}

fun NavGraphBuilder.forgotScreen(
    onNavigateNext: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    composable(
        route = AppRoutes.RestoreAccess.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIM_DURATION_600)
            ) + fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIM_DURATION_600)
            ) + fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val viewModel = hiltViewModel<RestoreAccessViewModel>()

        RestoreAccessScreen(
            viewModel = viewModel,
            onNavigateNext = onNavigateNext,
            onNavigateBack = onNavigateBack
        )
    }
}