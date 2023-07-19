/*
 * Create by Medvediev Viktor
 * last update: 12.07.23, 16:48
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.view.state

sealed interface ProfileViewState {
    object Loading : ProfileViewState // hasLoggedIn = unknown
    object LoggedIn : ProfileViewState // hasLoggedIn = true
    object NotLoggedIn : ProfileViewState // hasLoggedIn = false
}