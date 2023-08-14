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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mvproject.datingapp.R
import com.mvproject.datingapp.navigation.BottomNavItem
import com.mvproject.datingapp.navigation.NavConstants
import com.mvproject.datingapp.ui.screens.main.chat.ChatScreen
import com.mvproject.datingapp.ui.screens.main.chat.ChatViewModel
import com.mvproject.datingapp.utils.ANIM_DURATION_600

val Chat = BottomNavItem(NavConstants.ROUTE_CHAT, R.drawable.ic_nav_chat)

fun NavController.navigateToChat(navOptions: NavOptions? = null) {
    this.navigate(Chat.route, navOptions)
}

fun NavGraphBuilder.chatScreen(
    onNavigationLikes: () -> Unit = {},
    onNavigationSympathy: (String) -> Unit = {},
    onNavigationChat: (String) -> Unit = {}
) {
    composable(
        route = Chat.route,
        enterTransition = {
            fadeIn(animationSpec = tween(ANIM_DURATION_600))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(ANIM_DURATION_600))
        }
    ) {
        val chatViewModel = hiltViewModel<ChatViewModel>()

        ChatScreen(
            viewModel = chatViewModel,
            onNavigationSympathy = onNavigationSympathy,
            onNavigationChat = onNavigationChat,
            onNavigationLikes = onNavigationLikes
        )
    }
}