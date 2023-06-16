/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 18:03
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.actions

import com.mvproject.datingapp.data.ProfileGender

sealed class ProfileCreateAction {
    data class UpdateEmail(val text: String) : ProfileCreateAction()
    data class UpdatePassword(val text: String) : ProfileCreateAction()
    data class UpdateCode(val text: String) : ProfileCreateAction()
    data class UpdateName(val text: String) : ProfileCreateAction()
    data class UpdateGender(val gender: ProfileGender) : ProfileCreateAction()
    data class UpdateBirthdate(val date: String) : ProfileCreateAction()
    data class UpdateInterest(val interest: String) : ProfileCreateAction()
    data class UpdateImage(val srcUri: String) : ProfileCreateAction()
    object NextStep : ProfileCreateAction()
    object PrevStep : ProfileCreateAction()
    object Complete : ProfileCreateAction()
}