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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.dating.DatingFilterScreen
import com.mvproject.datingapp.ui.screens.main.dating.DatingFilterViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToDatingFilter(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.DatingFilter.route, navOptions)
}

fun NavController.navigateToDatingFilterClearStack() {
    this.popBackStack()
    this.navigate(AppRoutes.DatingFilter.route) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.datingFilterScreen(
    onNavigationBack: () -> Unit = {}
) {
    composable(
        route = AppRoutes.DatingFilter.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val datingFilterViewModel = hiltViewModel<DatingFilterViewModel>()

        DatingFilterScreen(
            viewModel = datingFilterViewModel,
            onNavigationBack = onNavigationBack
        )
    }
}