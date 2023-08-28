/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 17:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.likes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.SmallColorButton
import com.mvproject.datingapp.ui.components.composable.candidate.CandidateLikeBlurredView
import com.mvproject.datingapp.ui.components.composable.candidate.CandidateLikeView
import com.mvproject.datingapp.ui.screens.main.likes.action.LikesAction
import com.mvproject.datingapp.ui.screens.main.likes.state.LikesState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun LikesScreen(
    viewModel: LikesViewModel,
    onNavigationMatch: (String) -> Unit = {},
    onNavigationActivation: () -> Unit = {},
) {
    val likesState by viewModel.likesState.collectAsStateWithLifecycle()

    LikesView(
        state = likesState,
        onAction = viewModel::processAction,
        onMatch = onNavigationMatch,
        onActivateClick = onNavigationActivation
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikesView(
    state: LikesState = LikesState(),
    onActivateClick: () -> Unit = {},
    onAction: (LikesAction) -> Unit = {},
    onMatch: (String) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title_likes),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displayLarge
                    )
                },
                actions = {
                    if (!state.isPlanActivated) {
                        SmallColorButton(
                            modifier = Modifier.padding(end = MaterialTheme.dimens.size16),
                            title = stringResource(id = R.string.btn_title_likes_activate),
                            logo = painterResource(id = R.drawable.ic_buy_pro),
                            onClick = onActivateClick
                        )
                    }
                }
            )
        },
        bottomBar = { NavigationBar() {} },
        contentWindowInsets = WindowInsets.navigationBars
    ) { paddingValues ->
        when {
            state.lastBothLikeUser != null -> {
                LaunchedEffect(state.lastBothLikeUser) {
                    onMatch(state.lastBothLikeUser.id.toString())
                    onAction(LikesAction.BothMatchShown)
                }
            }

            else -> {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(
                            vertical = MaterialTheme.dimens.size16,
                            horizontal = MaterialTheme.dimens.size16
                        ),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size16),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size16)
                ) {
                    items(state.candidates) { current ->
                        if (state.isPlanActivated) {
                            CandidateLikeView(
                                modifier = Modifier
                                    .height(MaterialTheme.dimens.size290),
                                currentUser = current,
                                onLike = {
                                    onAction(LikesAction.Like(current))
                                },
                                onDisLike = {
                                    onAction(LikesAction.Dislike(current))
                                }
                            )
                        } else {
                            CandidateLikeBlurredView(
                                modifier = Modifier
                                    .height(MaterialTheme.dimens.size290),
                                currentUser = current,
                                onLike = {
                                    onAction(LikesAction.Like(current))
                                },
                                onDisLike = {
                                    onAction(LikesAction.Dislike(current))
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLikesView() {
    DatingAppTheme {
        LikesView()
    }
}