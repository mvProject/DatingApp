/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 12:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.chat.state

import com.mvproject.datingapp.dummy.SympathyUser

data class ChatMessageState(
    val logged: String = "20",
    val sender: SympathyUser? = null,
    val isUserBlocked: Boolean = false,
)