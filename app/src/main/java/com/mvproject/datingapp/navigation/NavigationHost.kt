package com.mvproject.datingapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.navigation.forgotScreen
import com.mvproject.datingapp.ui.screens.authorization.restoreAccess.navigation.navigateToForgot
import com.mvproject.datingapp.ui.screens.authorization.signin.navigation.loginScreen
import com.mvproject.datingapp.ui.screens.authorization.signin.navigation.navigateToSignInClearStack
import com.mvproject.datingapp.ui.screens.authorization.signup.navigation.navigateToSignUp
import com.mvproject.datingapp.ui.screens.authorization.signup.navigation.signUpScreen
import com.mvproject.datingapp.ui.screens.main.profile.navigation.navigateToProfileClearStack
import com.mvproject.datingapp.ui.screens.main.profile.navigation.profileScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        loginScreen(
            onNavigationForgotAccess = navController::navigateToForgot,
            onNavigationSignUp = navController::navigateToSignUp,
            onNavigationHome = navController::navigateToProfileClearStack,
        )


        forgotScreen(
            onNavigateBack = navController::popBackStack,
            onNavigateNext = navController::navigateToSignInClearStack
        )

        signUpScreen(
            onNavigateBack = navController::popBackStack,
            onNavigateNext = navController::navigateToSignInClearStack
        )
        /*
                               likesScreen()
                               chatScreen()
                               datingScreen()
                       */

        profileScreen(
            onNavigationLogout = navController::navigateToSignInClearStack
        )
    }
}

fun NavController.bottomNavigateToRoute(route: String) {
    this.navigate(route) {
        popUpTo(this@bottomNavigateToRoute.graph.findStartDestination().id)
        launchSingleTop = true
    }
}