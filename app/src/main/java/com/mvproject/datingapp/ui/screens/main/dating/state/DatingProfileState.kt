/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 12:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating.state

import com.mvproject.datingapp.data.model.PreviewModel

data class DatingProfileState(
    val isLocal: Boolean = false,
    val previewUser: PreviewModel = PreviewModel(),
    val userPhotos: List<String> = emptyList(),
    val matchPhotos: List<Int> = emptyList(),
)