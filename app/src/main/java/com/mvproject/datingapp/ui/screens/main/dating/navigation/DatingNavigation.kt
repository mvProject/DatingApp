/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 10:43
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
import androidx.navigation.compose.composable
import com.mvproject.datingapp.R
import com.mvproject.datingapp.navigation.BottomNavItem
import com.mvproject.datingapp.navigation.NavConstants
import com.mvproject.datingapp.ui.screens.main.dating.DatingScreen
import com.mvproject.datingapp.ui.screens.main.dating.DatingViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

val Dating = BottomNavItem(
    route = NavConstants.ROUTE_DATING,
    title = R.string.bottom_bar_dating_route,
    logo = R.drawable.ic_nav_dating
)

fun NavController.navigateToDatingClearStack() {
    this.navigate(Dating.route) {
        popUpTo(this@navigateToDatingClearStack.graph.id) {
            inclusive = false
        }
    }
}

fun NavGraphBuilder.datingScreen(
    onNavigationFilter: () -> Unit = {},
    onNavigationDetail: (String) -> Unit = {},
    onNavigationMatch: (String) -> Unit = {}
) {
    composable(
        route = Dating.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val datingViewModel = hiltViewModel<DatingViewModel>()

        DatingScreen(
            viewModel = datingViewModel,
            onNavigationFilter = onNavigationFilter,
            onNavigationDetail = onNavigationDetail,
            onNavigationMatch = onNavigationMatch
        )
    }
}