/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 16:52
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.previewFull.profile.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.previewFull.profile.ProfileFullPreviewScreen
import com.mvproject.datingapp.ui.screens.main.previewFull.profile.ProfileFullPreviewViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToProfileFullPreview() {
    this.navigate(AppRoutes.ProfileFullPreview.route)
}

fun NavGraphBuilder.profileFullPreviewScreen(
    onNavigationBack: () -> Unit = {}
) {
    composable(
        route = AppRoutes.ProfileFullPreview.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val profileFullPreviewViewModel = hiltViewModel<ProfileFullPreviewViewModel>()

        ProfileFullPreviewScreen(
            viewModel = profileFullPreviewViewModel,
            onNavigationBack = onNavigationBack
        )
    }
}