/*
 * Create by Medvediev Viktor
 * last update: 19.07.23, 17:31
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.profile.edit.EditOptionScreen
import com.mvproject.datingapp.ui.screens.main.profile.edit.EditOptionViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToEditOption(navOptions: NavOptions? = null) {
    this.navigate(AppRoutes.EditOption.route, navOptions)
}

fun NavController.navigateToEditOption(optionId: String) {
    this.navigate("${AppRoutes.EditOption.route}/$optionId")
}

fun NavController.navigateToEditOptionClearStack() {
    this.popBackStack()
    this.navigate(AppRoutes.EditOption.route) {
        launchSingleTop = true
    }
}

private const val optionIdArg = "optionId"

internal class EditOptionArgs(val optionId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[optionIdArg]) as String)
}

@OptIn(ExperimentalAnimationApi::class)
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