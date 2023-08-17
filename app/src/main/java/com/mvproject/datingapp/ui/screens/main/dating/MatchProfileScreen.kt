/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 17:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.input.chat.MatchMessageInput
import com.mvproject.datingapp.ui.screens.main.dating.state.MatchProfileState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.EMO_GREETING_1
import com.mvproject.datingapp.utils.EMO_GREETING_2
import com.mvproject.datingapp.utils.EMO_GREETING_3
import com.mvproject.datingapp.utils.EMO_GREETING_4
import com.mvproject.datingapp.utils.EMO_GREETING_5
import com.mvproject.datingapp.utils.SHAPE_RADIUS_PERCENT_100

@Composable
fun MatchProfileScreen(
    viewModel: MatchProfileViewModel,
    onNavigationBack: () -> Unit = {},
    onNavigationChat: (String, String) -> Unit = { _, _ -> }
) {
    val matchProfileState by viewModel.matchProfileState.collectAsStateWithLifecycle()
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
        onDispose {
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }
    }

    MatchProfileView(
        state = matchProfileState,
        onBackClick = onNavigationBack,
        onMessageClick = onNavigationChat
    )
}

@Composable
fun MatchProfileView(
    state: MatchProfileState = MatchProfileState(),
    onMessageClick: (String, String) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = state.profilePhoto),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Icon(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.dimens.size32,
                    end = MaterialTheme.dimens.size16
                )
                .size(MaterialTheme.dimens.size40)
                .clickable {
                    onBackClick()
                }
                .align(Alignment.TopEnd),
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Column(
            modifier = Modifier
                .systemBarsPadding()
                .imePadding()
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size16)
                .padding(vertical = MaterialTheme.dimens.size16)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(MaterialTheme.dimens.size48),
                painter = painterResource(id = R.drawable.ic_heart_press),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.profile_candidate_match_title),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.dimens.font32,
                textAlign = TextAlign.Center
            )

            val descriptionText = if (state.isFemale)
                R.string.profile_candidate_match_female_description
            else
                R.string.profile_candidate_match_male_description

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = descriptionText),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            MatchMessageInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.dimens.size4
                    ),
                onSendMessage = { message ->
                    onMessageClick(state.id, message)
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .clickable {
                            onMessageClick(state.id, EMO_GREETING_1)
                        }
                        .border(
                            width = MaterialTheme.dimens.size1,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(SHAPE_RADIUS_PERCENT_100)
                        )
                        .padding(
                            vertical = MaterialTheme.dimens.size8,
                            horizontal = MaterialTheme.dimens.size20
                        ),
                    text = EMO_GREETING_1,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    modifier = Modifier
                        .clickable {
                            onMessageClick(state.id, EMO_GREETING_2)
                        }
                        .border(
                            width = MaterialTheme.dimens.size1,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(SHAPE_RADIUS_PERCENT_100)
                        )
                        .padding(
                            vertical = MaterialTheme.dimens.size8,
                            horizontal = MaterialTheme.dimens.size20
                        ),
                    text = EMO_GREETING_2,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    modifier = Modifier
                        .clickable {
                            onMessageClick(state.id, EMO_GREETING_3)
                        }
                        .border(
                            width = MaterialTheme.dimens.size1,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(SHAPE_RADIUS_PERCENT_100)
                        )
                        .padding(
                            vertical = MaterialTheme.dimens.size8,
                            horizontal = MaterialTheme.dimens.size20
                        ),
                    text = EMO_GREETING_3,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    modifier = Modifier
                        .clickable {
                            onMessageClick(state.id, EMO_GREETING_4)
                        }
                        .border(
                            width = MaterialTheme.dimens.size1,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(SHAPE_RADIUS_PERCENT_100)
                        )
                        .padding(
                            vertical = MaterialTheme.dimens.size8,
                            horizontal = MaterialTheme.dimens.size20
                        ),
                    text = EMO_GREETING_4,
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    modifier = Modifier
                        .clickable {
                            onMessageClick(state.id, EMO_GREETING_5)
                        }
                        .border(
                            width = MaterialTheme.dimens.size1,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(SHAPE_RADIUS_PERCENT_100)
                        )
                        .padding(
                            vertical = MaterialTheme.dimens.size8,
                            horizontal = MaterialTheme.dimens.size20
                        ),
                    text = EMO_GREETING_5,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMatchProfileView() {
    DatingAppTheme {
        MatchProfileView()
    }
}