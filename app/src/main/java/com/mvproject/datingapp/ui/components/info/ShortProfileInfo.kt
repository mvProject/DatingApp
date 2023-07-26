/*
 * Create by Medvediev Viktor
 * last update: 24.07.23, 19:48
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_COMA_SPACE

@Composable
fun ShortProfileInfo(
    modifier: Modifier = Modifier,
    profileName: String = "TestName",
    profileAge: Int = 22,
    frontColor: Color = MaterialTheme.colorScheme.onPrimary,
    backColor: Color = MaterialTheme.colorScheme.primary,
    profileInterest: ProfileInterest = ProfileInterest.INTEREST_CHAT
) {
    Column(
        modifier = modifier.wrapContentWidth()
    ) {
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = buildAnnotatedString {
                append(profileName)
                append(STRING_COMA_SPACE)
                withStyle(
                    style = SpanStyle(
                        fontWeight = MaterialTheme.typography.labelSmall.fontWeight
                    )
                ) {
                    append(profileAge.toString())
                }
            },
            color = frontColor,
            style = MaterialTheme.typography.titleLarge,
            fontSize = MaterialTheme.dimens.font24
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size10))

        Row(
            Modifier
                .wrapContentWidth()
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
                painter = painterResource(id = profileInterest.logo),
                contentDescription = stringResource(id = profileInterest.title),
                tint = frontColor
            )

            Text(
                modifier = Modifier
                    .padding(start = MaterialTheme.dimens.size12),
                text = stringResource(id = profileInterest.title),
                style = MaterialTheme.typography.labelSmall,
                color = frontColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShortProfileInfo() {
    DatingAppTheme {
        ShortProfileInfo()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewShortProfileInfo() {
    DatingAppTheme(darkTheme = true) {
        ShortProfileInfo()
    }
}