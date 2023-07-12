/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signin.action

sealed class SignInAction {
    data class SignWithCredentialIn(val login: String, val password: String) : SignInAction()
    data class SignWithGoogleIn(val token: String) : SignInAction()
    object SignWithFacebookIn : SignInAction()
}