/*
 * Create by Medvediev Viktor
 * last update: 27.06.23, 13:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.authorization.restoreAccess.state

import com.mvproject.datingapp.utils.STRING_EMPTY
import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed

data class RestoreAccessDataState(
    val isLoading: Boolean = false,
    val email: String = STRING_EMPTY,
    val password: String = STRING_EMPTY,
    val code: String = STRING_EMPTY,
    val currentStep: RestoreAccessState = RestoreAccessState.EMAIL_REQUEST,
    val isCodeWasSend: StateEvent = consumed,
    val isPasswordWasChange: StateEvent = consumed,
)