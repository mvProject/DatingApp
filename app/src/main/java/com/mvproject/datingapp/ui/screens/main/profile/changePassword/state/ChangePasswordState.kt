/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 19:45
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.changePassword.state

import com.mvproject.datingapp.utils.STRING_EMPTY
import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed

data class ChangePasswordState(
    val isLoading: Boolean = false,
    val isPasswordWasChange: StateEvent = consumed,
    val profilePassword: String = STRING_EMPTY,
)