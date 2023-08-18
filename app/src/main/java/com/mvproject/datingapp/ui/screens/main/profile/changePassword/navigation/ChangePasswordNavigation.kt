/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.changePassword.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.profile.changePassword.ChangePasswordScreen
import com.mvproject.datingapp.ui.screens.main.profile.changePassword.ChangePasswordViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToChangePassword(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.ChangePassword.route, navOptions)
}

fun NavGraphBuilder.changePasswordScreen(
    onNavigationBack: () -> Unit = {}
) {
    composable(
        route = AppRoutes.ChangePassword.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val changePasswordViewModel = hiltViewModel<ChangePasswordViewModel>()

        ChangePasswordScreen(
            viewModel = changePasswordViewModel,
            onNavigationBack = onNavigationBack
        )
    }
}