/*
 * Create by Medvediev Viktor
 * last update: 08.08.23, 17:32
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.model.UserChatMessage
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.toTimeReadableFormat
import kotlin.time.Duration.Companion.hours

@Composable
fun ChatMessageItemView(
    modifier: Modifier = Modifier,
    userChatMessage: UserChatMessage,
    loggedUserId: String,
    onMessageLike: () -> Unit = {}
) {
    val messageReceived = loggedUserId != userChatMessage.senderId
    val likeLogo = if (userChatMessage.isMessageLiked)
        R.drawable.ic_heart_press
    else
        R.drawable.ic_heart

    val alignment = if (messageReceived) Alignment.Start else Alignment.End

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimens.size16)
    ) {
        if (messageReceived) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(
                                topStart = MaterialTheme.dimens.size22,
                                topEnd = MaterialTheme.dimens.size22,
                                bottomStart = MaterialTheme.dimens.size6,
                                bottomEnd = MaterialTheme.dimens.size22
                            )
                        )
                        .padding(
                            vertical = MaterialTheme.dimens.size6,
                            horizontal = MaterialTheme.dimens.size12
                        ),
                    text = userChatMessage.message,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = MaterialTheme.dimens.font17
                )

                Spacer(modifier = Modifier.width(MaterialTheme.dimens.size5))

                Icon(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.size32)
                        .clickable {
                            onMessageLike()
                        },
                    painter = painterResource(id = likeLogo),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.End)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = MaterialTheme.dimens.size10)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(
                                topStart = MaterialTheme.dimens.size22,
                                topEnd = MaterialTheme.dimens.size22,
                                bottomStart = MaterialTheme.dimens.size22,
                                bottomEnd = MaterialTheme.dimens.size6
                            )
                        )
                        .align(Alignment.Center)
                        .padding(
                            vertical = MaterialTheme.dimens.size5,
                            horizontal = MaterialTheme.dimens.size12
                        ),
                    text = userChatMessage.message,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = MaterialTheme.dimens.font17
                )

                if (userChatMessage.isMessageLiked) {
                    Box(
                        modifier = Modifier
                            .size(MaterialTheme.dimens.size16)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .align(Alignment.BottomStart)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(MaterialTheme.dimens.size16)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_heart_press),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .align(alignment),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.dimens.size6,
                        end = MaterialTheme.dimens.size6
                    ),
                text = userChatMessage.sendDate.toTimeReadableFormat(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )

            if (!messageReceived) {
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.size4))

                val icon = if (userChatMessage.isMessageRead)
                    R.drawable.ic_double_check
                else
                    R.drawable.ic_check

                Icon(
                    modifier = Modifier
                        .height(MaterialTheme.dimens.size20)
                        .width(MaterialTheme.dimens.size22),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatListItem() {
    DatingAppTheme {
        ChatMessageItemView(
            userChatMessage = UserChatMessage(
                message = "hello",
                senderId = "1",
                receiverId = "20",
                sendDate = System.currentTimeMillis() - 4.hours.inWholeMilliseconds
            ),
            loggedUserId = "20"
        )
    }
}