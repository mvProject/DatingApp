/*
 * Create by Medvediev Viktor
 * last update: 19.06.23, 18:12
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.state

sealed interface ProfileViewState {
    object Loading : ProfileViewState // hasLoggedIn = unknown
    object LoggedIn : ProfileViewState // hasLoggedIn = true
    object NotLoggedIn : ProfileViewState // hasLoggedIn = false
}