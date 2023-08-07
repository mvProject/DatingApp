/*
 * Create by Medvediev Viktor
 * last update: 19.06.23, 17:23
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.signup.state

import com.mvproject.datingapp.data.enums.profile.ProfileGender
import com.mvproject.datingapp.data.enums.profile.ProfileInterest
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.ui.screens.authorization.signup.state.SignUpState.Companion.completeProgress
import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class SignUpProfileDataState(
    val isLoading: Boolean = false,
    val email: String = STRING_EMPTY,
    val name: String = STRING_EMPTY,
    val birthdate: Long = LONG_ZERO,
    val password: String = STRING_EMPTY,
    val code: String = STRING_EMPTY,
    val location: UserLocation = UserLocation(),
    val interest: ProfileInterest = ProfileInterest.INTEREST_LOVE,
    val gender: ProfileGender = ProfileGender.MALE,
    val currentStep: SignUpState = SignUpState.EMAIL,
    val images: List<String> = emptyList(),
    val currentStepProgress: Float = SignUpState.EMAIL.completeProgress(),
    val isComplete: Boolean = false
)