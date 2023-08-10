/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 14:01
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.likes

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.dummy.DummyScreen
import com.mvproject.datingapp.navigation.BottomNavItem
import com.mvproject.datingapp.navigation.NavConstants
import com.mvproject.datingapp.utils.ANIM_DURATION_600
import timber.log.Timber

val Likes = BottomNavItem(
    route = NavConstants.ROUTE_LIKES,
    title = R.string.bottom_bar_likes_route,
    logo = R.drawable.ic_nav_likes
)

fun NavController.navigateToLikes(navOptions: NavOptions? = null) {
    this.navigate(Likes.route, navOptions)
}

fun NavGraphBuilder.likesScreen(
    onAction: () -> Unit = {}
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
        Timber.w("testing LikesNavigation")
        DummyScreen(
            title = Likes.route,
        )
    }
}