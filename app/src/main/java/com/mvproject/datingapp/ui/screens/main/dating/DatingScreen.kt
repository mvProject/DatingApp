/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 17:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.ui.components.buttons.CircleButton
import com.mvproject.datingapp.ui.components.composable.EmptyCandidatesView
import com.mvproject.datingapp.ui.components.indicators.StoryIndicator
import com.mvproject.datingapp.ui.components.info.ShortProfileInfo
import com.mvproject.datingapp.ui.components.swipe.Direction
import com.mvproject.datingapp.ui.components.swipe.rememberSwipeableCardState
import com.mvproject.datingapp.ui.components.swipe.swipableCard
import com.mvproject.datingapp.ui.screens.main.dating.action.DatingAction
import com.mvproject.datingapp.ui.screens.main.dating.state.DatingState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.ALPHA_10
import com.mvproject.datingapp.utils.ALPHA_35
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.SCALE_80
import com.mvproject.datingapp.utils.STEP_1
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.calculatAgeMillis
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun DatingScreen(
    viewModel: DatingViewModel,
    onNavigationFilter: () -> Unit = {},
    onNavigationDetail: () -> Unit = {}
) {
    val datingState by viewModel.datingState.collectAsStateWithLifecycle()

    DatingView(
        state = datingState,
        onFilterClick = onNavigationFilter,
        onDetailClick = onNavigationDetail,
        onAction = viewModel::processAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatingView(
    state: DatingState = DatingState(),
    onFilterClick: () -> Unit = {},
    onDetailClick: () -> Unit = {},
    onAction: (DatingAction) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        modifier = Modifier
                            .height(MaterialTheme.dimens.size32)
                            .width(MaterialTheme.dimens.size168),
                        painter = painterResource(id = R.drawable.ic_profile_title_logo),
                        contentDescription = null
                    )
                },
                navigationIcon = {
                    if (state.lastDislikeUser != null) {
                        IconButton(onClick = { onAction(DatingAction.LastUserRevenue) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_profile_refund),
                                contentDescription = stringResource(id = R.string.screen_title_profile),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = onFilterClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_profile_filter),
                            contentDescription = stringResource(id = R.string.screen_title_profile),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        bottomBar = { NavigationBar() {} },
        contentWindowInsets = WindowInsets.navigationBars
    ) { paddingValues ->
        when {
            state.candidates.isEmpty() -> {
                EmptyCandidatesView(
                    modifier = Modifier.padding(paddingValues),
                    profileLogo = state.profileLogo,
                    onFilterClick = onFilterClick
                )
            }

            state.likeAnimationState || state.dislikeAnimationState -> {

            }

            else -> {
                val scope = rememberCoroutineScope()

                val swipeStates = state.candidates.reversed()
                    .map { it to rememberSwipeableCardState() }

                swipeStates.forEach { (current, state) ->
                    if (state.swipedDirection == null) {
                        Box(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxSize()
                                .padding(MaterialTheme.dimens.size16)
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxSize(),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Image(
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .swipableCard(
                                            state = state,
                                            onSwiped = { direction ->
                                                when (direction) {
                                                    Direction.Left -> {
                                                        onAction(DatingAction.Dislike(current))
                                                    }

                                                    Direction.Right -> {
                                                        onAction(DatingAction.Like(current))
                                                    }

                                                    else -> {
                                                        Timber.w("testing swipe ${state.swipedDirection}")
                                                    }
                                                }

                                            },
                                            onSwipeCancel = {
                                                Timber.w("testing Cancelled swipe")
                                            }
                                        ),
                                    painter = painterResource(id = current.profilePictureUrl),
                                    contentDescription = null
                                )
                            }

                            StoryIndicator(
                                modifier = Modifier
                                    .padding(top = MaterialTheme.dimens.size8)
                                    .align(Alignment.TopStart),
                                totalDots = current.photos.size - STEP_1,
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
                                profileName = current.name,
                                profileAge = calculatAgeMillis(current.birthdate),
                                profileInterest = ProfileInterest.fromStringOrDefault(current.interest)
                            )

                            Image(
                                modifier = Modifier
                                    .padding(
                                        top = MaterialTheme.dimens.size24,
                                        end = MaterialTheme.dimens.size16
                                    )
                                    .size(MaterialTheme.dimens.size40)
                                    .clickable {
                                        onDetailClick()
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
                                CircleButton(
                                    onClick = {
                                        scope.launch {
                                            state.swipe(Direction.Left)
                                            onAction(DatingAction.Dislike(current))
                                        }
                                    },
                                    logo = painterResource(id = R.drawable.ic_profile_dislike)
                                )
                                CircleButton(
                                    onClick = {
                                        scope.launch {
                                            state.swipe(Direction.Right)
                                            onAction(DatingAction.Like(current))
                                        }
                                    },
                                    logo = painterResource(id = R.drawable.ic_profile_like)
                                )
                            }
                        }
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = state.likeAnimationState,
            enter = scaleIn(
                animationSpec = tween(easing = LinearEasing),
                initialScale = SCALE_80
            ),
            exit = scaleOut(targetScale = WEIGHT_1)
        ) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(MaterialTheme.dimens.size16)
                    .background(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = ALPHA_35))
            ) {
                Image(
                    modifier = Modifier
                        .height(MaterialTheme.dimens.size96)
                        .width(MaterialTheme.dimens.size180)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.logo_like),
                    contentDescription = null
                )
            }
        }

        AnimatedVisibility(
            visible = state.dislikeAnimationState,
            enter = scaleIn(
                animationSpec = tween(easing = LinearEasing),
                initialScale = SCALE_80
            ),
            exit = scaleOut(targetScale = WEIGHT_1)
        ) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(MaterialTheme.dimens.size16)
                    .background(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = ALPHA_10))
            ) {
                Image(
                    modifier = Modifier
                        .height(MaterialTheme.dimens.size96)
                        .width(MaterialTheme.dimens.size180)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.logo_dislike),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDatingView() {
    DatingAppTheme {
        DatingView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewDatingView() {
    DatingAppTheme(darkTheme = true) {
        DatingView()
    }
}