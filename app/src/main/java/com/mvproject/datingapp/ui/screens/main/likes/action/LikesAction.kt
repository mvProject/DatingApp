/*
 * Create by Medvediev Viktor
 * last update: 10.08.23, 18:12
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.likes.action

import com.mvproject.datingapp.data.dummy.MatchUser

sealed class LikesAction {
    data class Like(val user: MatchUser) : LikesAction()
    data class Dislike(val user: MatchUser) : LikesAction()
    data object BothMatchShown : LikesAction()
}