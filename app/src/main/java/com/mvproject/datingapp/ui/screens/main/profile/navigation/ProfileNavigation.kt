/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 14:01
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.mvproject.datingapp.R
import com.mvproject.datingapp.navigation.BottomNavItem
import com.mvproject.datingapp.navigation.NavConstants
import com.mvproject.datingapp.ui.screens.main.profile.ProfileScreen
import com.mvproject.datingapp.ui.screens.main.profile.ProfileViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600
import timber.log.Timber

val Profile = BottomNavItem(NavConstants.ROUTE_PROFILE, R.drawable.ic_nav_profile)

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(Profile.route, navOptions)
}

fun NavController.navigateToProfileClearStack() {
    this.popBackStack()
    this.navigate(Profile.route) {
        launchSingleTop = true
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileScreen(
    onNavigationLogout: () -> Unit = {}
) {
    composable(
        route = Profile.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        Timber.w("testing ProfileNavigation")
        val profileViewModel = hiltViewModel<ProfileViewModel>()
        ProfileScreen(
            viewModel = profileViewModel,
            onNavigationLogout = onNavigationLogout
        )
    }
}