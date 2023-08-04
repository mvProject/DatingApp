/*
 * Create by Medvediev Viktor
 * last update: 01.08.23, 18:01
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.dummy

import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class ChatUser(
    val id: Int = INT_ZERO,
    val userImage: Int = INT_ZERO,
    val userName: String = STRING_EMPTY,
    val userLastMessage: String = STRING_EMPTY,
    val userLastMessageTime: Long = LONG_ZERO,
    val unreadCount: Int = INT_ZERO
)