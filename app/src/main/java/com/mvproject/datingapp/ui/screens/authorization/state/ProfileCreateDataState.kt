/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 18:00
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.state

import com.mvproject.datingapp.data.ProfileGender

data class ProfileCreateDataState(
    val isLoading: Boolean = false,
    val email: String = "",
    val name: String = "",
    val birthdate: String = "",
    val password: String = "",
    val code: String = "",
    val interest: String = "",
    val gender: ProfileGender = ProfileGender.MALE,
    val currentStep: ProfileCreateState = ProfileCreateState.SELECT_GENDER,
    val images: List<String> = emptyList(),
    val currentStepProgress: Float = 0f
)