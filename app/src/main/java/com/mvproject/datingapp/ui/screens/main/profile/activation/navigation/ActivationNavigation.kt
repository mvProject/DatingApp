/*
 * Create by Medvediev Viktor
 * last update: 09.08.23, 13:09
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.activation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.profile.activation.ActivationScreen
import com.mvproject.datingapp.ui.screens.main.profile.activation.ActivationViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToActivationScreen(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.Activation.route, navOptions)
}

fun NavGraphBuilder.activationScreen(
    onNavigationBack: () -> Unit = {}
) {
    composable(
        route = AppRoutes.Activation.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val activationViewModel = hiltViewModel<ActivationViewModel>()
        ActivationScreen(
            viewModel = activationViewModel,
            onNavigationBack = onNavigationBack
        )
    }
}