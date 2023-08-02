/*
 * Create by Medvediev Viktor
 * last update: 01.08.23, 16:47
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.dummy

import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class SympathyUser(
    val id: Int = INT_ZERO,
    val userImage: Int = INT_ZERO,
    val userName: String = STRING_EMPTY,
    val isUserOnline: Boolean = false
)