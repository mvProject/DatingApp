/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 10:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating.action

import com.mvproject.datingapp.data.dummy.MatchUser

sealed class DatingAction {
    data class Like(val user: MatchUser) : DatingAction()
    data class Dislike(val user: MatchUser) : DatingAction()
    data object LastUserRevenue : DatingAction()
}