/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signup.actions

import com.mvproject.datingapp.data.enums.profile.ProfileGender
import com.mvproject.datingapp.data.enums.profile.ProfileInterest
import com.mvproject.datingapp.data.model.UserLocation

sealed class SignUpAction {
    data class UpdateEmail(val text: String) : SignUpAction()
    data class UpdatePassword(val text: String) : SignUpAction()
    data class UpdateName(val text: String) : SignUpAction()
    data class UpdateGender(val gender: ProfileGender) : SignUpAction()
    data class UpdateBirthdate(val date: Long) : SignUpAction()
    data class UpdateLocation(val location: UserLocation) : SignUpAction()
    data class UpdateInterest(val interest: ProfileInterest) : SignUpAction()
    data class UpdatePhotos(val uris: List<String>) : SignUpAction()
    object ResendCode : SignUpAction()
    object NextStep : SignUpAction()
    object PrevStep : SignUpAction()
}