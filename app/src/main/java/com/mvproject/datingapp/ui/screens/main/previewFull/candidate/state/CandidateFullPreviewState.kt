/*
 * Create by Medvediev Viktor
 * last update: 14.08.23, 09:40
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.previewFull.candidate.state

import com.mvproject.datingapp.data.dummy.MatchUser

data class CandidateFullPreviewState(
    val previewUser: MatchUser = MatchUser()
)