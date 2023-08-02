/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 12:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.chat.state

import com.mvproject.datingapp.dummy.ChatUser
import com.mvproject.datingapp.dummy.SympathyUser
import com.mvproject.datingapp.utils.STRING_EMPTY

data class ChatState(
    val profileLogo: String = STRING_EMPTY,
    val sympathyUsers: List<SympathyUser> = emptyList(),
    val chatUsers: List<ChatUser> = emptyList()
)