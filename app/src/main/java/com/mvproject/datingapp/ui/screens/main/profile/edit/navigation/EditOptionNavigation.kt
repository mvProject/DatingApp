/*
 * Create by Medvediev Viktor
 * last update: 19.07.23, 17:31
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.profile.edit.EditOptionScreen
import com.mvproject.datingapp.ui.screens.main.profile.edit.EditOptionViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToEditOption(optionId: String) {
    this.navigate("${AppRoutes.EditOption.route}/$optionId")
}

private const val optionIdArg = "optionId"

internal class EditOptionArgs(val optionId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[optionIdArg]) as String)
}

fun NavGraphBuilder.editOptionScreen(
    onNavigationBack: () -> Unit = {}
) {
    composable(
        route = "${AppRoutes.EditOption.route}/{$optionIdArg}",
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val editOptionViewModel = hiltViewModel<EditOptionViewModel>()

        EditOptionScreen(
            viewModel = editOptionViewModel,
            onNavigationBack = onNavigationBack
        )
    }
}