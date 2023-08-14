/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 16:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.preview.state

import com.mvproject.datingapp.data.model.User

data class ProfilePreviewState(
    val previewUser: User = User(),
)