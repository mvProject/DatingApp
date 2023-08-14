/*
 * Create by Medvediev Viktor
 * last update: 08.08.23, 17:32
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.data.dummy.SympathyUser
import com.mvproject.datingapp.data.dummy.sympathyUsers
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun SympathyUserView(
    sympathyUser: SympathyUser,
    onSympathyClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(MaterialTheme.dimens.size74)
            .height(MaterialTheme.dimens.size88)
            .clickable {
                onSympathyClick()
            }
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(MaterialTheme.dimens.size60)
                    .clip(CircleShape),
                painter = painterResource(id = sympathyUser.userImage),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

            Text(
                text = sympathyUser.userName,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall,
                fontSize = MaterialTheme.dimens.font12
            )
        }

        if (sympathyUser.isUserOnline) {
            Box(
                modifier = Modifier
                    .padding(end = MaterialTheme.dimens.size10)
                    .size(MaterialTheme.dimens.size12)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.TopEnd)
            ) {
                Box(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.size8)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSympathyUserView() {
    DatingAppTheme {
        SympathyUserView(
            sympathyUser = sympathyUsers.first()
        )
    }
}