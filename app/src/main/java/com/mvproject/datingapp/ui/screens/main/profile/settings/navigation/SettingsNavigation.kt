/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:59
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.settings.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.profile.settings.SettingsScreen
import com.mvproject.datingapp.ui.screens.main.profile.settings.SettingsViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.Settings.route, navOptions)
}

fun NavGraphBuilder.settingsScreen(
    onNavigationLogout: () -> Unit = {},
    onNavigationBack: () -> Unit = {},
    onNavigationChange: () -> Unit = {}
) {
    composable(
        route = AppRoutes.Settings.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val settingsViewModel = hiltViewModel<SettingsViewModel>()

        SettingsScreen(
            viewModel = settingsViewModel,
            onNavigationLogout = onNavigationLogout,
            onNavigationChange = onNavigationChange,
            onNavigationBack = onNavigationBack
        )
    }
}