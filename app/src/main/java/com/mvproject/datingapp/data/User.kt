/*
 * Create by Medvediev Viktor
 * last update: 14.06.23, 17:19
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data

import android.net.Uri

data class User(
    val name: String? = null,
    val birthdate: String = "",
    val email: String = "",
    val password: String = "",
    val interest: String = "",
    val gender: String = "",
    val uid: String = "",
    val profilePictureUrl: String? = null,
    val images: List<Uri> = emptyList()
)