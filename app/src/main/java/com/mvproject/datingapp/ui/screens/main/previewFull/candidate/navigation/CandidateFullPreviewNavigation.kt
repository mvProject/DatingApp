/*
 * Create by Medvediev Viktor
 * last update: 14.08.23, 09:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.previewFull.candidate.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.previewFull.candidate.CandidateFullPreviewScreen
import com.mvproject.datingapp.ui.screens.main.previewFull.candidate.CandidateFullPreviewViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToCandidateFullPreview(profileId: String) {
    this.navigate("${AppRoutes.CandidatePreview.route}/$profileId")
}

private const val profileIdArg = "profileId"

internal class CandidateFullPreviewArgs(val profileId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[profileIdArg]) as String)
}

fun NavGraphBuilder.candidateFullPreviewScreen(
    onNavigationBack: () -> Unit = {}
) {
    composable(
        route = "${AppRoutes.CandidatePreview.route}/{$profileIdArg}",
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val candidateFullPreviewViewModel = hiltViewModel<CandidateFullPreviewViewModel>()

        CandidateFullPreviewScreen(
            viewModel = candidateFullPreviewViewModel,
            onNavigationBack = onNavigationBack
        )
    }
}