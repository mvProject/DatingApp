/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 12:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating.state

import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class MatchProfileState(
    val id: String = STRING_EMPTY,
    val isFemale: Boolean = false,
    val profilePhoto: Int = INT_ZERO,
)