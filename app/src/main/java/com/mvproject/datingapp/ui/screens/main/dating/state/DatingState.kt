/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 12:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating.state

import com.mvproject.datingapp.dummy.MatchUser
import com.mvproject.datingapp.utils.STRING_EMPTY

data class DatingState(
    val profileLogo: String = STRING_EMPTY,
    val matchedUsers: List<MatchUser> = emptyList(),
    val lastDislikeUser: MatchUser? = null,
    val candidates: List<MatchUser> = emptyList(),
    val likeAnimationState: Boolean = false,
    val dislikeAnimationState: Boolean = false,
)