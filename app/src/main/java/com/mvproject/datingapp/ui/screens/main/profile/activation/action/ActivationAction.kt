/*
 * Create by Medvediev Viktor
 * last update: 09.08.23, 14:08
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.activation.action

import com.mvproject.datingapp.data.model.ActivationPlan

sealed class ActivationAction {
    data class SelectPlan(val data: ActivationPlan) : ActivationAction()
    data object ActivatePlan : ActivationAction()
    data object DeactivatePlan : ActivationAction()
}