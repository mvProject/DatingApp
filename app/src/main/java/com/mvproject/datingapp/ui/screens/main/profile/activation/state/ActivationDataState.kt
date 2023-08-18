/*
 * Create by Medvediev Viktor
 * last update: 09.08.23, 14:09
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.activation.state

import com.mvproject.datingapp.data.ActivationData
import com.mvproject.datingapp.data.model.ActivationPlan
import com.mvproject.datingapp.utils.LONG_ZERO

data class ActivationDataState(
    val activationStatus: Boolean = false,
    val activationExpires: Long = LONG_ZERO,
    val selectedPlan: ActivationPlan = ActivationData.activationPlans.first()
)