/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 12:55
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.ColorButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.ALPHA_25
import com.mvproject.datingapp.utils.SCALE_40
import com.mvproject.datingapp.utils.SCALE_60
import com.mvproject.datingapp.utils.SCALE_80
import com.mvproject.datingapp.utils.SCALE_FULL
import com.mvproject.datingapp.utils.STRING_EMPTY

@Composable
fun EmptyCandidatesView(
    modifier: Modifier = Modifier,
    profileLogo: String = STRING_EMPTY,
    onFilterClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(MaterialTheme.dimens.size16),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(
            MaterialTheme.dimens.size1,
            MaterialTheme.colorScheme.outline
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Box(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.size220)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            alpha = SCALE_FULL - SCALE_80
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = ALPHA_25),
                                shape = CircleShape
                            )
                    )
                }

                Box(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.size174)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            alpha = SCALE_FULL - SCALE_60
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = ALPHA_25),
                                shape = CircleShape
                            )
                    )
                }

                Box(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.size128)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            alpha = SCALE_FULL - SCALE_40
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = ALPHA_25),
                                shape = CircleShape
                            )
                    )
                }

                AsyncImage(
                    model = Uri.parse(profileLogo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(MaterialTheme.dimens.size88)
                        .border(
                            MaterialTheme.dimens.size1,
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size32))

            Text(
                text = stringResource(id = R.string.profile_candidate_over_title),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size12))

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size74),
                text = stringResource(id = R.string.profile_candidate_over_description),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size32))

            ColorButton(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size16)
                    .width(MaterialTheme.dimens.size180),
                title = stringResource(id = R.string.btn_title_filter),
                logo = painterResource(id = R.drawable.ic_profile_filter),
                onClick = onFilterClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyCandidatesView() {
    DatingAppTheme {
        EmptyCandidatesView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewEmptyCandidatesView() {
    DatingAppTheme(darkTheme = true) {
        EmptyCandidatesView()
    }
}