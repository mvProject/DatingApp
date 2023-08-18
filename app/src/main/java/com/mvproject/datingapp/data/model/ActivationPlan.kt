/*
 * Create by Medvediev Viktor
 * last update: 08.08.23, 19:27
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

import androidx.annotation.StringRes
import com.mvproject.datingapp.data.enums.ActivationPlanType
import com.mvproject.datingapp.utils.INT_ZERO

data class ActivationPlan(
    val currentPrice: Int = INT_ZERO,
    val oldPrice: Int = INT_ZERO,
    val pricePerMonth: Int = INT_ZERO,
    @StringRes val pricePerMonthTitle: Int = INT_ZERO,
    val monthDuration: Int = INT_ZERO,
    @StringRes val monthDurationTitle: Int = INT_ZERO,
    val planType: ActivationPlanType = ActivationPlanType.BASE,
)