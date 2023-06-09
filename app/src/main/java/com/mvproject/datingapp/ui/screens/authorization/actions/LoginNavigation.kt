/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 18:18
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.actions

sealed class LoginNavigation {
    object SignIn : LoginNavigation()
    object SignUp : LoginNavigation()
    object SignInGoogle : LoginNavigation()
    object SignInFacebook : LoginNavigation()
    object RestorePassword : LoginNavigation()
}