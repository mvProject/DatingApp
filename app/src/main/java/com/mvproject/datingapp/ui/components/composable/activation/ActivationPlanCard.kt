/*
 * Create by Medvediev Viktor
 * last update: 09.08.23, 17:57
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.activation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.ActivationData
import com.mvproject.datingapp.data.enums.ActivationPlanType
import com.mvproject.datingapp.data.model.ActivationPlan
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.WEIGHT_2

@Composable
fun ActivationPlanCard(
    modifier: Modifier = Modifier,
    activationPlan: ActivationPlan,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val backgroundColor = if (isSelected)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.outlineVariant

    val borderColor = if (isSelected)
        MaterialTheme.colorScheme.secondary
    else
        MaterialTheme.colorScheme.outline

    val borderWidth = if (isSelected)
        MaterialTheme.dimens.size2
    else
        MaterialTheme.dimens.size1

    Box(
        modifier = modifier
            .wrapContentSize()
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier
                .padding(top = MaterialTheme.dimens.size10)
                .height(MaterialTheme.dimens.size160)
                .width(MaterialTheme.dimens.size109)
                .clip(MaterialTheme.shapes.medium)
                .background(color = backgroundColor)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = MaterialTheme.shapes.medium
                )

                .padding(MaterialTheme.dimens.size8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimens.size10),
                text = stringResource(
                    id = activationPlan.monthDurationTitle,
                    activationPlan.monthDuration
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.weight(WEIGHT_2))

            if (activationPlan.oldPrice > INT_ZERO) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                textDecoration = TextDecoration.LineThrough
                            )
                        ) {
                            append("$${activationPlan.oldPrice}")
                        }
                    },
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(WEIGHT_1))

            Text(
                text = "$${activationPlan.currentPrice}",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
                fontSize = MaterialTheme.dimens.font24,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(
                    id = activationPlan.pricePerMonthTitle,
                    activationPlan.pricePerMonth
                ),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall,
                fontSize = MaterialTheme.dimens.font11,
                textAlign = TextAlign.Center
            )
        }

        val planText = when (activationPlan.planType) {
            ActivationPlanType.BASE -> STRING_EMPTY
            ActivationPlanType.POPULAR -> stringResource(id = R.string.activation_plan_popular)
            ActivationPlanType.FAVORABLE -> stringResource(id = R.string.activation_plan_favorable)
        }

        if (planText.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = MaterialTheme.shapes.extraSmall
                    )
                    .padding(
                        horizontal = MaterialTheme.dimens.size6
                    )
                    .align(Alignment.TopCenter),
                text = planText,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall,
                fontSize = MaterialTheme.dimens.font12,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActivationPlanCard() {
    DatingAppTheme {
        ActivationPlanCard(activationPlan = ActivationData.activationPlans.first())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActivationPlanCard2() {
    DatingAppTheme {
        ActivationPlanCard(activationPlan = ActivationData.activationPlans.last())
    }
}