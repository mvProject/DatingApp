/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit.state

import com.mvproject.datingapp.data.enums.profile.ProfileInterest
import com.mvproject.datingapp.data.model.User
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.utils.INT_ZERO

data class EditDataState(
    val profileInterest: ProfileInterest = ProfileInterest.INTEREST_LOVE,
    val profileLocation: UserLocation = UserLocation(),
    val profileHeight: Int = INT_ZERO,
    val currentProfile: User = User()
)
