/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.view.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvproject.datingapp.R
import com.mvproject.datingapp.navigation.BottomNavItem
import com.mvproject.datingapp.navigation.NavConstants
import com.mvproject.datingapp.ui.screens.main.profile.view.ProfileScreen
import com.mvproject.datingapp.ui.screens.main.profile.view.ProfileViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

val Profile = BottomNavItem(
    route = NavConstants.ROUTE_PROFILE,
    title = R.string.bottom_bar_profile_route,
    logo = R.drawable.ic_nav_profile
)

fun NavController.navigateToProfileClearStack() {
    this.navigate(Profile.route) {
        popUpTo(this@navigateToProfileClearStack.graph.id) {
            inclusive = false
        }
    }
}

fun NavGraphBuilder.profileScreen(
    onNavigationLogout: () -> Unit = {},
    onNavigationSettings: () -> Unit = {},
    onNavigationActivation: () -> Unit = {},
    onNavigationEdit: () -> Unit = {}
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
        val profileViewModel = hiltViewModel<ProfileViewModel>()
        ProfileScreen(
            viewModel = profileViewModel,
            onNavigationLogout = onNavigationLogout,
            onNavigationSettings = onNavigationSettings,
            onNavigationEdit = onNavigationEdit,
            onNavigationActivation = onNavigationActivation
        )
    }
}