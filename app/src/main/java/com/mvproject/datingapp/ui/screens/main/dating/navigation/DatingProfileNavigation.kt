/*
 * Create by Medvediev Viktor
 * last update: 19.07.23, 17:31
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.dating.DatingProfileScreen
import com.mvproject.datingapp.ui.screens.main.dating.DatingProfileViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToDatingProfile(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.DatingProfile.route, navOptions)
}

fun NavController.navigateToDatingProfile(profileId: String) {
    this.navigate("${AppRoutes.DatingProfile.route}/$profileId")
}

fun NavController.navigateToDatingProfileClearStack() {
    this.popBackStack()
    this.navigate(AppRoutes.DatingProfile.route) {
        launchSingleTop = true
    }
}

private const val profileIdArg = "profileId"

internal class DatingProfileArgs(val profileId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[profileIdArg]) as String)
}

fun NavGraphBuilder.datingProfileScreen(
    onNavigationBack: () -> Unit = {}
) {
    composable(
        route = "${AppRoutes.DatingProfile.route}/{$profileIdArg}",
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val datingProfileViewModel = hiltViewModel<DatingProfileViewModel>()

        DatingProfileScreen(
            viewModel = datingProfileViewModel,
            onNavigationBack = onNavigationBack
        )
    }
}