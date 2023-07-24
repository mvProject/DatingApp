/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 14:01
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.mvproject.datingapp.dummy.DummyScreen
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.utils.ANIM_DURATION_600
import timber.log.Timber

fun NavController.navigateToDating(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.Dating.route, navOptions)
}

fun NavController.navigateToDatingClearStack() {
    this.popBackStack()
    this.navigate(AppRoutes.Dating.route) {
        launchSingleTop = true
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.datingScreen(
    onAction: () -> Unit = {}
) {
    composable(
        route = AppRoutes.Dating.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        Timber.w("testing DatingNavigation")
        DummyScreen(
            title = AppRoutes.Dating.route,
        )
    }
}