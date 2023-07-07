/*
 * Create by Medvediev Viktor
 * last update: 14.06.23, 17:19
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class User(
    val name: String = STRING_EMPTY,
    val birthdate: Long = LONG_ZERO,
    val email: String = STRING_EMPTY,
    val password: String = STRING_EMPTY,
    val interest: String = STRING_EMPTY,
    val gender: String = STRING_EMPTY,
    val uid: String = STRING_EMPTY,
    val location: String = STRING_EMPTY,
    val profilePictureUrl: String = STRING_EMPTY,
    val photos: List<String> = emptyList()
)