/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 19:38
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.settings.state

import com.mvproject.datingapp.data.enums.ProfileGender
import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class SettingsState(
    val profileName: String = STRING_EMPTY,
    val profileEmail: String = STRING_EMPTY,
    val profileGender: ProfileGender = ProfileGender.MALE,
    val profileBirthDate: Long = LONG_ZERO,
)