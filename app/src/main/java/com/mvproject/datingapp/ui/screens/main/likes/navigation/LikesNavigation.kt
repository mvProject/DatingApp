/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 10:05
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.likes.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.R
import com.mvproject.datingapp.navigation.BottomNavItem
import com.mvproject.datingapp.navigation.NavConstants
import com.mvproject.datingapp.ui.screens.main.likes.LikesScreen
import com.mvproject.datingapp.ui.screens.main.likes.LikesViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

val Likes = BottomNavItem(
    route = NavConstants.ROUTE_LIKES,
    title = R.string.bottom_bar_likes_route,
    logo = R.drawable.ic_nav_likes
)

fun NavController.navigateToLikes(navOptions: NavOptions? = null) {
    this.navigate(Likes.route, navOptions)
}

fun NavGraphBuilder.likesScreen(
    onNavigationActivation: () -> Unit = {},
    onNavigationMatch: (String) -> Unit = {}
) {
    composable(
        route = Likes.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val likesViewModel = hiltViewModel<LikesViewModel>()

        LikesScreen(
            viewModel = likesViewModel,
            onNavigationMatch = onNavigationMatch,
            onNavigationActivation = onNavigationActivation
        )
    }
}