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
import com.mvproject.datingapp.ui.screens.authorization.signup.navigation.navigateToSignUpClearStack
import com.mvproject.datingapp.ui.screens.authorization.signup.navigation.signUpScreen
import com.mvproject.datingapp.ui.screens.main.dating.datingScreen
import com.mvproject.datingapp.ui.screens.main.dating.navigateToDatingClearStack
import com.mvproject.datingapp.ui.screens.main.profile.navigation.navigateToProfileClearStack
import com.mvproject.datingapp.ui.screens.main.profile.navigation.profileScreen
import com.mvproject.datingapp.ui.screens.main.questionaire.navigation.navigateToProfileQuestionsClearStack
import com.mvproject.datingapp.ui.screens.main.questionaire.navigation.profileQuestionsScreen

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
            onNavigationSignUp = navController::navigateToSignUpClearStack,
            onNavigationHome = navController::navigateToProfileClearStack,
        )


        forgotScreen(
            onNavigateBack = navController::popBackStack,
            onNavigateNext = navController::navigateToSignInClearStack
        )

        signUpScreen(
            onNavigateBack = navController::popBackStack,
            onNavigateNext = navController::navigateToProfileQuestionsClearStack
        )

        /*
                               likesScreen()
                               chatScreen()
                       */

        profileQuestionsScreen(
            onNavigateNext = navController::navigateToDatingClearStack
        )

        profileScreen(
            onNavigationLogout = navController::navigateToSignInClearStack
        )

        datingScreen()

    }
}

fun NavController.bottomNavigateToRoute(route: String) {
    this.navigate(route) {
        popUpTo(this@bottomNavigateToRoute.graph.findStartDestination().id)
        launchSingleTop = true
    }
}