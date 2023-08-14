/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 12:53
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.candidate

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.dummy.MatchUser
import com.mvproject.datingapp.ui.components.buttons.CircleButton
import com.mvproject.datingapp.ui.components.indicators.StoryIndicator
import com.mvproject.datingapp.ui.components.info.ShortProfileInfo
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STEP_1
import com.mvproject.datingapp.utils.calculatAgeMillis

@Composable
fun CandidatePreview(
    modifier: Modifier = Modifier,
    currentUser: MatchUser = MatchUser(),
    onDetailClick: (String) -> Unit = {},
    isButtonEnabled: Boolean = true,
    onLike: () -> Unit = {},
    onDislike: () -> Unit = {},
) {
    Box(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = MaterialTheme.shapes.medium
        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = currentUser.profilePictureUrl),
                contentDescription = null
            )
        }

        StoryIndicator(
            modifier = Modifier
                .padding(top = MaterialTheme.dimens.size8)
                .align(Alignment.TopStart),
            totalDots = currentUser.photos.size - STEP_1,
            selectedIndex = INT_ZERO
        )

        ShortProfileInfo(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.dimens.size24,
                    start = MaterialTheme.dimens.size16
                )
                .align(Alignment.TopStart),
            frontColor = MaterialTheme.colorScheme.primary,
            backColor = Color.Transparent,
            profileName = currentUser.name,
            profileAge = calculatAgeMillis(currentUser.birthdate),
            profileInterest = currentUser.interest
        )

        Image(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.dimens.size24,
                    end = MaterialTheme.dimens.size16
                )
                .size(MaterialTheme.dimens.size40)
                .clickable {
                    onDetailClick(currentUser.id.toString())
                }
                .align(Alignment.TopEnd),
            painter = painterResource(id = R.drawable.ic_profile_about),
            contentDescription = null
        )

        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    horizontal = MaterialTheme.dimens.size24,
                    vertical = MaterialTheme.dimens.size32
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val color = if (isButtonEnabled)
                Color.Unspecified
            else MaterialTheme.colorScheme.outline
            CircleButton(
                onClick = onDislike,
                iconColor = color,
                logo = painterResource(id = R.drawable.ic_profile_dislike)
            )
            CircleButton(
                onClick = onLike,
                iconColor = color,
                logo = painterResource(id = R.drawable.ic_profile_like)
            )
        }
    }
}