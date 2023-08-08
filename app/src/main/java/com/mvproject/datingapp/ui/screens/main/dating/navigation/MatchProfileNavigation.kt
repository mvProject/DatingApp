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
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.dating.MatchProfileScreen
import com.mvproject.datingapp.ui.screens.main.dating.MatchProfileViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600


fun NavController.navigateToMatchProfile(matchProfileId: String) {
    this.navigate("${AppRoutes.MatchProfile.route}/$matchProfileId")
}

private const val matchProfileIdArg = "matchProfileId"

internal class MatchProfileArgs(val matchProfileId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[matchProfileIdArg]) as String)
}

fun NavGraphBuilder.matchProfileScreen(
    onNavigationBack: () -> Unit = {},
    onNavigationChat: (String, String) -> Unit = { _, _ -> }
) {
    composable(
        route = "${AppRoutes.MatchProfile.route}/{$matchProfileIdArg}",
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val matchProfileViewModel = hiltViewModel<MatchProfileViewModel>()

        MatchProfileScreen(
            viewModel = matchProfileViewModel,
            onNavigationBack = onNavigationBack,
            onNavigationChat = onNavigationChat
        )
    }
}