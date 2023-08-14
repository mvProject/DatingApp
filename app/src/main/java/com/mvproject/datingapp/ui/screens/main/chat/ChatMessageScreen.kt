/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 17:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.model.UserChatMessage
import com.mvproject.datingapp.ui.components.buttons.MenuButton
import com.mvproject.datingapp.ui.components.composable.chat.ChatMessageItemView
import com.mvproject.datingapp.ui.components.dialog.BottomDialog
import com.mvproject.datingapp.ui.components.input.chat.ChatMessageInput
import com.mvproject.datingapp.ui.screens.main.chat.action.ChatAction
import com.mvproject.datingapp.ui.screens.main.chat.state.ChatMessageState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun ChatMessageScreen(
    viewModel: ChatMessageViewModel,
    onNavigationDetail: (String) -> Unit = {},
    onNavigationBack: () -> Unit = {},
) {
    val chatMessageState by viewModel.chatMessageState.collectAsStateWithLifecycle()
    val chatMessages = viewModel.messages

    ChatMessageView(
        state = chatMessageState,
        chatMessages = chatMessages,
        onChatAction = viewModel::processAction,
        onDetailClick = onNavigationDetail,
        onBackClick = onNavigationBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatMessageView(
    state: ChatMessageState = ChatMessageState(),
    chatMessages: List<UserChatMessage> = emptyList(),
    onChatAction: (ChatAction) -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val isChatOptionOpen = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.dimens.size10)
                    ) {
                        state.sender?.let { user ->
                            Image(
                                modifier = Modifier
                                    .size(MaterialTheme.dimens.size32)
                                    .clip(CircleShape),
                                painter = painterResource(id = user.userImage),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )

                            Spacer(Modifier.width(MaterialTheme.dimens.size8))
                            Text(
                                text = user.userName,
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_navigate_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            isChatOptionOpen.value = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dot_menu),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) { paddingValues ->


        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .imePadding()
        ) {
            val lazyListState = rememberLazyListState()

            LaunchedEffect(key1 = chatMessages.size) {
                lazyListState.scrollToItem(chatMessages.size)
            }

            LazyColumn(
                state = lazyListState,
                modifier = Modifier.weight(WEIGHT_1)
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.chat_messages_today),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = MaterialTheme.dimens.font14,
                        textAlign = TextAlign.Center
                    )
                }

                items(chatMessages) { message ->
                    ChatMessageItemView(
                        userChatMessage = message,
                        loggedUserId = state.logged,
                        onMessageLike = {
                            onChatAction(ChatAction.ToggleLikeMessage(message))
                        }
                    )
                }
            }
            Spacer(Modifier.height(MaterialTheme.dimens.size8))

            if (state.isUserBlocked) {
                Divider(
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .padding(
                            top = MaterialTheme.dimens.size14,
                            bottom = MaterialTheme.dimens.size40,
                            start = MaterialTheme.dimens.size16,
                            end = MaterialTheme.dimens.size16,
                        ),
                    text = stringResource(id = R.string.chat_user_blocked),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            } else {
                ChatMessageInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = MaterialTheme.dimens.size8,
                            horizontal = MaterialTheme.dimens.size16
                        ),
                    onSendMessage = { message ->
                        onChatAction(ChatAction.AddMessage(message))
                    }
                )
            }

        }

        BottomDialog(
            modifier = Modifier
                .padding(paddingValues),
            isVisible = isChatOptionOpen
        ) {
            Column()
            {
                MenuButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(id = R.string.btn_title_view_profile),
                    btnColor = MaterialTheme.colorScheme.surfaceVariant,
                    isEnabled = !state.isUserBlocked,
                    titleColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        isChatOptionOpen.value = false
                        onDetailClick(state.sender?.id.toString())
                    }
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline
                )
                MenuButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(id = R.string.btn_title_block_report),
                    btnColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleColor = MaterialTheme.colorScheme.onError,
                    onClick = {
                        onChatAction(ChatAction.BlockUser)
                        isChatOptionOpen.value = false
                    }
                )
                MenuButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(id = R.string.btn_title_cancel),
                    btnColor = MaterialTheme.colorScheme.primary,
                    titleColor = MaterialTheme.colorScheme.onPrimary,
                    isBold = true,
                    onClick = {
                        isChatOptionOpen.value = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatMessageView() {
    DatingAppTheme {
        ChatMessageView()
    }
}







