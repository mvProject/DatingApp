/*
 * Create by Medvediev Viktor
 * last update: 27.06.23, 19:06
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.restoreAccess.action

sealed class RestoreAccessAction {
    data class UpdateEmail(val text: String) : RestoreAccessAction()
    data class UpdatePassword(val text: String) : RestoreAccessAction()
    object ResendCode : RestoreAccessAction()
    object NextStep : RestoreAccessAction()
    object PrevStep : RestoreAccessAction()
    object SendCode : RestoreAccessAction()
    object SendPassword : RestoreAccessAction()
    object SendCodeComplete : RestoreAccessAction()
    object SendPasswordComplete : RestoreAccessAction()
}