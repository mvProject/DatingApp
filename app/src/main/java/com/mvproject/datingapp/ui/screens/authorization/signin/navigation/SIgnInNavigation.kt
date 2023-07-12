/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signin.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.authorization.signin.SignInScreen
import com.mvproject.datingapp.ui.screens.authorization.signin.SignInViewModel

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.SignIn.route, navOptions)
}

fun NavController.navigateToSignInClearStack() {
    this.popBackStack()
    this.navigate(AppRoutes.SignIn.route) {
        launchSingleTop = true
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginScreen(
    onNavigationSignUp: () -> Unit = {},
    onNavigationHome: () -> Unit = {},
    onNavigationForgotAccess: () -> Unit = {}
) {
    composable(
        AppRoutes.SignIn.route
    ) {
        val loginViewModel = hiltViewModel<SignInViewModel>()

        SignInScreen(
            viewModel = loginViewModel,
            onNavigationHome = onNavigationHome,
            onNavigationSignUp = onNavigationSignUp,
            onNavigationForgotAccess = onNavigationForgotAccess
        )
    }
}