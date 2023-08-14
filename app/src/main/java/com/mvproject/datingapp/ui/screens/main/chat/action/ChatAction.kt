/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 10:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.chat.action

import com.mvproject.datingapp.data.model.UserChatMessage

sealed class ChatAction {
    data class ToggleLikeMessage(val message: UserChatMessage) : ChatAction()
    data class AddMessage(val message: String) : ChatAction()
    data object BlockUser : ChatAction()
}