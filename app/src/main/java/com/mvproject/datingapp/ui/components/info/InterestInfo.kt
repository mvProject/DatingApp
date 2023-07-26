/*
 * Create by Medvediev Viktor
 * last update: 11.07.23, 11:39
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun InterestInfo(
    modifier: Modifier = Modifier,
    selectedInterest: ProfileInterest,
    frontColor: Color = MaterialTheme.colorScheme.onPrimary,
    backColor: Color = MaterialTheme.colorScheme.primary,
) {
    Row(
        modifier
            .background(
                color = backColor,
                shape = MaterialTheme.shapes.large
            )
            .border(
                MaterialTheme.dimens.size1,
                color = frontColor,
                shape = MaterialTheme.shapes.large
            )
            .padding(
                vertical = MaterialTheme.dimens.size2,
                horizontal = MaterialTheme.dimens.size8
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .padding(start = MaterialTheme.dimens.size8),
            painter = painterResource(id = selectedInterest.logo),
            contentDescription = stringResource(id = selectedInterest.title),
            tint = frontColor
        )

        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.dimens.size12),
            text = stringResource(id = selectedInterest.title),
            style = MaterialTheme.typography.labelLarge,
            color = frontColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInterestInfo() {
    DatingAppTheme {
        InterestInfo(selectedInterest = ProfileInterest.INTEREST_CHAT)
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewInterestInfo() {
    DatingAppTheme(darkTheme = true) {
        InterestInfo(selectedInterest = ProfileInterest.INTEREST_DATE)
    }
}