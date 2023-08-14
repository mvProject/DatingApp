/*
 * Create by Medvediev Viktor
 * last update: 02.08.23, 11:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.chat.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.ui.screens.main.chat.ChatMessageScreen
import com.mvproject.datingapp.ui.screens.main.chat.ChatMessageViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

fun NavController.navigateToChatMessageScreen(profileId: String, profileMessage: String? = null) {
    this.navigate("${AppRoutes.ChatMessage.route}/$profileId/$profileMessage") {
        if (profileMessage != null) {
            this@navigateToChatMessageScreen.popBackStack()
        }
    }
}

private const val profileIdArg = "profileId"
private const val profileMessageArg = "profileMessage"

internal class ChatMessageArgs(val profileId: String, val profileMessage: String?) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                profileId = checkNotNull(savedStateHandle[profileIdArg]) as String,
                profileMessage = checkNotNull(savedStateHandle[profileMessageArg]) as String?
            )
}

fun NavGraphBuilder.chatMessageScreen(
    onNavigationBack: () -> Unit = {},
    onNavigationDetail: (String) -> Unit = {}
) {
    composable(
        route = "${AppRoutes.ChatMessage.route}/{$profileIdArg}/{$profileMessageArg}",
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val chatMessageViewModel = hiltViewModel<ChatMessageViewModel>()

        ChatMessageScreen(
            viewModel = chatMessageViewModel,
            onNavigationBack = onNavigationBack,
            onNavigationDetail = onNavigationDetail
        )
    }
}