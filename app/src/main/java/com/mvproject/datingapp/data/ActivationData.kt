/*
 * Create by Medvediev Viktor
 * last update: 09.08.23, 12:12
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data

import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ActivationPlanType
import com.mvproject.datingapp.data.model.ActivationInfo
import com.mvproject.datingapp.data.model.ActivationPlan

object ActivationData {
    val activationPlans = listOf(
        ActivationPlan(
            currentPrice = 20,
            pricePerMonth = 20,
            pricePerMonthTitle = R.string.price_per_month,
            monthDuration = 1,
            monthDurationTitle = R.string.activation_duration_month
        ),
        ActivationPlan(
            currentPrice = 90,
            oldPrice = 120,
            pricePerMonth = 15,
            pricePerMonthTitle = R.string.price_per_month,
            monthDuration = 6,
            monthDurationTitle = R.string.activation_duration_months,
            planType = ActivationPlanType.POPULAR
        ),
        ActivationPlan(
            currentPrice = 144,
            oldPrice = 240,
            pricePerMonth = 12,
            pricePerMonthTitle = R.string.price_per_month,
            monthDuration = 12,
            monthDurationTitle = R.string.activation_duration_months,
            planType = ActivationPlanType.FAVORABLE
        ),
    )

    val activationInfos = listOf(
        ActivationInfo(
            title = R.string.activation_view_likes_title,
            description = R.string.activation_view_likes_description,
            logo = R.drawable.ic_logo_likes_view
        ),
        ActivationInfo(
            title = R.string.activation_unlimited_likes_title,
            description = R.string.activation_unlimited_likes_description,
            logo = R.drawable.ic_logo_unlimited_likes
        ),
        ActivationInfo(
            title = R.string.activation_swipe_cancel_title,
            description = R.string.activation_swipe_cancel_description,
            logo = R.drawable.ic_logo_swipe_cancel
        ),
        ActivationInfo(
            title = R.string.activation_no_ads_title,
            description = R.string.activation_no_ads_description,
            logo = R.drawable.ic_logo_no_ads
        )
    )
}