/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 19:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.changePassword.action

sealed class ChangePasswordAction {
    data class UpdatePassword(val text: String) : ChangePasswordAction()
    object SendPasswordComplete : ChangePasswordAction()
}