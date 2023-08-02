/*
 * Create by Medvediev Viktor
 * last update: 01.08.23, 19:47
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.dummy.ChatUser
import com.mvproject.datingapp.dummy.chatUsers
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.ALPHA_3
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.WEIGHT_3
import com.mvproject.datingapp.utils.toTimeReadableFormat

@Composable
fun ChatUserView(
    modifier: Modifier = Modifier,
    chatUser: ChatUser,
    onChatClick: () -> Unit = {}
) {
    val color = if (chatUser.unreadCount > INT_ZERO)
        MaterialTheme.colorScheme.secondary.copy(alpha = ALPHA_3)
    else
        MaterialTheme.colorScheme.primary

    Column(
        modifier = modifier
            .background(color)
            .clickable {
                onChatClick()
            }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.dimens.size10,
                    horizontal = MaterialTheme.dimens.size16
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .size(MaterialTheme.dimens.size52)
                    .clip(CircleShape),
                painter = painterResource(id = chatUser.userImage),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size12)
                    .weight(WEIGHT_3)
            ) {
                Text(
                    text = chatUser.userName,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = chatUser.userLastMessage,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = chatUser.userLastMessageTime.toTimeReadableFormat(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall
                )

                if (chatUser.unreadCount > INT_ZERO) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(MaterialTheme.dimens.size100)
                            )
                            .padding(
                                horizontal = MaterialTheme.dimens.size10
                            ),
                        text = chatUser.unreadCount.toString(),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Divider(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.dimens.size80,
                    end = MaterialTheme.dimens.size16
                ),
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatUserView() {
    DatingAppTheme {
        ChatUserView(
            chatUser = chatUsers.first()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewChatUserView() {
    DatingAppTheme(darkTheme = true) {
        ChatUserView(
            chatUser = chatUsers.last()
        )
    }
}