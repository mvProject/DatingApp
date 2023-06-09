/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 18:18
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.actions

sealed class LoginDataAction {
    data class UpdateLogin(val text: String) : LoginDataAction()
    data class UpdatePassword(val text: String) : LoginDataAction()
}