/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signup.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.authorization.signup.SignUpScreen
import com.mvproject.datingapp.ui.screens.authorization.signup.SignUpViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.SignUp.route, navOptions)
}

fun NavGraphBuilder.signUpScreen(
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
) {
    composable(
        route = AppRoutes.SignUp.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val signUpViewModel = hiltViewModel<SignUpViewModel>()

        SignUpScreen(
            viewModel = signUpViewModel,
            onNavigateNext = onNavigateNext,
            onNavigateBack = onNavigateBack
        )
    }
}