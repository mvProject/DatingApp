/*
 * Create by Medvediev Viktor
 * last update: 02.08.23, 17:37
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class UserChatMessage(
    val message: String = STRING_EMPTY,
    val senderId: String = STRING_EMPTY,
    val isMessageLiked: Boolean = false,
    val isMessageRead: Boolean = false,
    val receiverId: String = STRING_EMPTY,
    val sendDate: Long = LONG_ZERO
)