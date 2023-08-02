/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 17:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.composable.ChatUserView
import com.mvproject.datingapp.ui.components.composable.CurrentLikesView
import com.mvproject.datingapp.ui.components.composable.SympathyUserView
import com.mvproject.datingapp.ui.screens.main.chat.state.ChatState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    onNavigationLikes: () -> Unit = {},
    onNavigationSympathy: (Int) -> Unit = {},
    onNavigationChat: (Int) -> Unit = {}
) {
    val chatState by viewModel.chatState.collectAsStateWithLifecycle()

    ChatView(
        state = chatState,
        onSympathyClick = onNavigationSympathy,
        onChatClick = onNavigationChat,
        onLikesClick = onNavigationLikes
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatView(
    state: ChatState = ChatState(),
    onSympathyClick: (Int) -> Unit = {},
    onChatClick: (Int) -> Unit = {},
    onLikesClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title_chats),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            )
        },
        bottomBar = { NavigationBar() {} },
        contentWindowInsets = WindowInsets.navigationBars
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.dimens.size16,
                        top = MaterialTheme.dimens.size16
                    ),
                text = stringResource(id = R.string.sympathies_block_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall,
                fontSize = MaterialTheme.dimens.font14
            )

            LazyRow(
                modifier = Modifier
                    .padding(vertical = MaterialTheme.dimens.size16)
                    .padding(horizontal = MaterialTheme.dimens.size8),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size8)
            ) {
                item {
                    CurrentLikesView(
                        profileLogo = state.profileLogo,
                        onLikesClick = onLikesClick
                    )
                }

                items(state.sympathyUsers) { user ->
                    SympathyUserView(
                        sympathyUser = user,
                        onSympathyClick = {
                            onSympathyClick(user.id)
                        }
                    )
                }
            }

            Divider(
                color = MaterialTheme.colorScheme.outline
            )

            Text(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.dimens.size16,
                        top = MaterialTheme.dimens.size16,
                        bottom = MaterialTheme.dimens.size16
                    ),
                text = stringResource(id = R.string.chats_block_title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall,
                fontSize = MaterialTheme.dimens.font14
            )

            LazyColumn(
                modifier = Modifier
            ) {
                items(state.chatUsers) { user ->
                    ChatUserView(
                        chatUser = user,
                        onChatClick = {
                            onChatClick(user.id)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatView() {
    DatingAppTheme {
        ChatView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewChatView() {
    DatingAppTheme(darkTheme = true) {
        ChatView()
    }
}






