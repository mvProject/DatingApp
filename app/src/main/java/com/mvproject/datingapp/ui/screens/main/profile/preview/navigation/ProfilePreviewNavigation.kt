/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 17:03
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.preview.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.profile.preview.ProfilePreviewScreen
import com.mvproject.datingapp.ui.screens.main.profile.preview.ProfilePreviewViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToProfilePreview() {
    this.navigate(AppRoutes.ProfilePreview.route)
}
fun NavGraphBuilder.profilePreviewScreen(
    onNavigationBack: () -> Unit = {},
    onNavigationDetail: () -> Unit = {}
) {
    composable(
        route = AppRoutes.ProfilePreview.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val profilePreviewViewModel = hiltViewModel<ProfilePreviewViewModel>()

        ProfilePreviewScreen(
            viewModel = profilePreviewViewModel,
            onNavigationBack = onNavigationBack,
            onNavigationDetail = onNavigationDetail
        )
    }
}