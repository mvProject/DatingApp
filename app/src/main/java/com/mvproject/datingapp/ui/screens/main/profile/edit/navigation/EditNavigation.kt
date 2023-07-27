/*
 * Create by Medvediev Viktor
 * last update: 19.07.23, 17:31
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.profile.edit.EditScreen
import com.mvproject.datingapp.ui.screens.main.profile.edit.EditViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToEdit(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.Edit.route, navOptions)
}

fun NavController.navigateToEditClearStack() {
    this.popBackStack()
    this.navigate(AppRoutes.Edit.route) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.editScreen(
    onNavigationBack: () -> Unit = {},
    onNavigationChange: (String) -> Unit = {}
) {
    composable(
        route = AppRoutes.Edit.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val editViewModel = hiltViewModel<EditViewModel>()

        EditScreen(
            viewModel = editViewModel,
            onNavigationChange = onNavigationChange,
            onNavigationBack = onNavigationBack
        )
    }
}