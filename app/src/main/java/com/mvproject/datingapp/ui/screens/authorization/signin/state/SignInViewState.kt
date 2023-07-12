/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signin.state

sealed interface SignInViewState {
    object Loading : SignInViewState // hasLoggedIn = unknown
    object LoggedIn : SignInViewState // hasLoggedIn = true
    object NotLoggedIn : SignInViewState // hasLoggedIn = false
}