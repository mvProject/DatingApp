/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 12:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.likes.state

import com.mvproject.datingapp.data.dummy.MatchUser
import com.mvproject.datingapp.data.dummy.matchCandidateUsers

data class LikesState(
    val isPlanActivated: Boolean = false,
    val candidates: List<MatchUser> = matchCandidateUsers,
    val lastBothLikeUser: MatchUser? = null,
)