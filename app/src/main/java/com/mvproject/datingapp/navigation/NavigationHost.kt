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
import com.mvproject.datingapp.ui.screens.main.chat.navigation.chatScreen
import com.mvproject.datingapp.ui.screens.main.dating.navigation.datingFilterScreen
import com.mvproject.datingapp.ui.screens.main.dating.navigation.datingProfileScreen
import com.mvproject.datingapp.ui.screens.main.dating.navigation.datingScreen
import com.mvproject.datingapp.ui.screens.main.dating.navigation.navigateToDatingClearStack
import com.mvproject.datingapp.ui.screens.main.dating.navigation.navigateToDatingFilter
import com.mvproject.datingapp.ui.screens.main.dating.navigation.navigateToDatingProfile
import com.mvproject.datingapp.ui.screens.main.likes.likesScreen
import com.mvproject.datingapp.ui.screens.main.likes.navigateToLikes
import com.mvproject.datingapp.ui.screens.main.profile.changePassword.navigation.changePasswordScreen
import com.mvproject.datingapp.ui.screens.main.profile.changePassword.navigation.navigateToChangePassword
import com.mvproject.datingapp.ui.screens.main.profile.edit.navigation.editOptionScreen
import com.mvproject.datingapp.ui.screens.main.profile.edit.navigation.editScreen
import com.mvproject.datingapp.ui.screens.main.profile.edit.navigation.navigateToEdit
import com.mvproject.datingapp.ui.screens.main.profile.edit.navigation.navigateToEditOption
import com.mvproject.datingapp.ui.screens.main.profile.settings.navigation.navigateToSettings
import com.mvproject.datingapp.ui.screens.main.profile.settings.navigation.settingsScreen
import com.mvproject.datingapp.ui.screens.main.profile.view.navigation.navigateToProfileClearStack
import com.mvproject.datingapp.ui.screens.main.profile.view.navigation.profileScreen
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
            onNavigationSignUp = navController::navigateToSignUp,
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

        profileQuestionsScreen(
            onNavigateNext = navController::navigateToDatingClearStack
        )

        profileScreen(
            onNavigationLogout = navController::navigateToSignInClearStack,
            onNavigationSettings = navController::navigateToSettings,
            onNavigationEdit = navController::navigateToEdit
        )

        settingsScreen(
            onNavigationLogout = navController::navigateToSignInClearStack,
            onNavigationChange = navController::navigateToChangePassword,
            onNavigationBack = navController::popBackStack
        )

        editScreen(
            onNavigationChange = navController::navigateToEditOption,
            onNavigationBack = navController::popBackStack
        )

        editOptionScreen(
            onNavigationBack = navController::popBackStack
        )

        changePasswordScreen(
            onNavigationBack = navController::popBackStack
        )

        datingScreen(
            onNavigationDetail = navController::navigateToDatingProfile,
            onNavigationFilter = navController::navigateToDatingFilter
        )

        datingProfileScreen(
            onNavigationBack = navController::popBackStack,
        )

        datingFilterScreen(
            onNavigationBack = navController::popBackStack
        )

        likesScreen()

        chatScreen(
            onNavigationSympathy = { },
            onNavigationChat = { },
            onNavigationLikes = navController::navigateToLikes
        )

        /*        navController.addOnDestinationChangedListener { controller, destination, arguments ->
                    val cont = controller.currentBackStack.value
                    Timber.e("testing after navigate to ${destination.route}")
                    cont.forEach {
                        Timber.e("testing stack is ${it.destination.route}")
                    }
                }*/
    }
}

fun NavController.bottomNavigateToRoute(route: String) {
    this.navigate(route) {
        popUpTo(this@bottomNavigateToRoute.graph.findStartDestination().id)
        launchSingleTop = true
    }
}