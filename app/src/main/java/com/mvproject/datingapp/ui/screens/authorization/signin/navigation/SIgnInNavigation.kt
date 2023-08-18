/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signin.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.authorization.signin.SignInScreen
import com.mvproject.datingapp.ui.screens.authorization.signin.SignInViewModel

fun NavController.navigateToSignInClearStack() {
    this.navigate(AppRoutes.SignIn.route) {
        popUpTo(this@navigateToSignInClearStack.graph.id) {
            inclusive = false
        }
    }
}

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