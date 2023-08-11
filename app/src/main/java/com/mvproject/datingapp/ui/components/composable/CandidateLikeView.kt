/*
 * Create by Medvediev Viktor
 * last update: 10.08.23, 18:17
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.dummy.MatchUser
import com.mvproject.datingapp.data.dummy.matchCandidateUsers
import com.mvproject.datingapp.ui.components.buttons.ImageButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.calculatAgeMillis

@Composable
fun CandidateLikeView(
    modifier: Modifier = Modifier,
    currentUser: MatchUser = MatchUser(),
    onLike: () -> Unit = {},
    onDisLike: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = currentUser.profilePictureUrl),
                contentDescription = currentUser.name,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${currentUser.name}, ${calculatAgeMillis(currentUser.birthdate)}",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.dimens.size48)
                ) {
                    ImageButton(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(WEIGHT_1),
                        onClick = onDisLike,
                        btnColor = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(MaterialTheme.dimens.sizeZero),
                        logo = painterResource(id = R.drawable.ic_profile_dislike)
                    )
                    ImageButton(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(WEIGHT_1),
                        onClick = onLike,
                        shape = RoundedCornerShape(MaterialTheme.dimens.sizeZero),
                        logo = painterResource(id = R.drawable.ic_profile_like)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCandidateLikeView() {
    DatingAppTheme {
        CandidateLikeView(
            modifier = Modifier
                .width(MaterialTheme.dimens.size180)
                .height(MaterialTheme.dimens.size290),
            currentUser = matchCandidateUsers.first()
        )
    }
}