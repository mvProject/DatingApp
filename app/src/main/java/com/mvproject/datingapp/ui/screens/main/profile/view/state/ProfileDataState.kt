/*
 * Create by Medvediev Viktor
 * last update: 19.07.23, 12:21
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.view.state

import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.utils.LONG_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

data class ProfileDataState(
    val profileName: String = STRING_EMPTY,
    val profileImage: String = STRING_EMPTY,
    val profileAge: Int = 12,
    val profileInterest: ProfileInterest = ProfileInterest.INTEREST_LOVE,
    val activationStatus: Boolean = false,
    val activationExpires: Long = LONG_ZERO,
    val activationPrice: Int = 20,
)