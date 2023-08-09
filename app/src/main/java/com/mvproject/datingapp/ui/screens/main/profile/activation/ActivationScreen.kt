/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 17:04
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.activation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.ActivationData.activationInfos
import com.mvproject.datingapp.data.ActivationData.activationPlans
import com.mvproject.datingapp.data.enums.ActivationPlanType
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.composable.ActivationInfoCard
import com.mvproject.datingapp.ui.components.composable.ActivationPlanCard
import com.mvproject.datingapp.ui.screens.main.profile.activation.action.ActivationAction
import com.mvproject.datingapp.ui.screens.main.profile.activation.state.ActivationDataState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.FLOAT_ZERO
import com.mvproject.datingapp.utils.INT_ZERO
import kotlinx.coroutines.delay

@Composable
fun ActivationScreen(
    viewModel: ActivationViewModel,
    onNavigationBack: () -> Unit = {}
) {
    val activationDataState by viewModel.activationDataState.collectAsStateWithLifecycle()

    ActivationView(
        state = activationDataState,
        onAction = viewModel::processAction,
        onBackClick = onNavigationBack
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ActivationView(
    state: ActivationDataState = ActivationDataState(),
    onAction: (ActivationAction) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.unlock_profile_title),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
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
                .padding(vertical = MaterialTheme.dimens.size16)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pages = activationInfos

            val pagerState = rememberPagerState(
                initialPage = INT_ZERO,
                initialPageOffsetFraction = FLOAT_ZERO
            ) {
                pages.size
            }
            LaunchedEffect(Unit) {
                while (true) {
                    delay(2000)
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + 1) % pagerState.pageCount,
                        animationSpec = tween(500)
                    )
                }
            }

            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                pageNestedScrollConnection = PagerDefaults
                    .pageNestedScrollConnection(Orientation.Horizontal),
                pageContent = { position ->
                    ActivationInfoCard(activationInfo = pages[position])
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { position ->
                    val color = if (pagerState.currentPage == position)
                        MaterialTheme.colorScheme.secondary
                    else
                        MaterialTheme.colorScheme.outline

                    Box(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.dimens.size4)
                            .clip(CircleShape)
                            .background(color)
                            .size(MaterialTheme.dimens.size8)

                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Divider(
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(activationPlans.size) { position ->
                    val current = activationPlans[position]
                    val isSelected = current == state.selectedPlan
                    ActivationPlanCard(
                        activationPlan = current,
                        isSelected = isSelected,
                        onClick = {
                            onAction(ActivationAction.SelectPlan(current))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size16),
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.terms_prefix))
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(id = R.string.terms_main))
                    }
                    append(stringResource(id = R.string.terms_suffix))
                },
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            val buttonText = when (state.selectedPlan.planType) {
                ActivationPlanType.BASE -> stringResource(
                    id = R.string.btn_title_profile_activate_month,
                    state.selectedPlan.currentPrice,
                    state.selectedPlan.monthDuration
                )

                ActivationPlanType.POPULAR -> stringResource(
                    id = R.string.btn_title_profile_activate_months,
                    state.selectedPlan.currentPrice,
                    state.selectedPlan.monthDuration
                )

                ActivationPlanType.FAVORABLE -> stringResource(
                    id = R.string.btn_title_profile_activate_months,
                    state.selectedPlan.currentPrice,
                    state.selectedPlan.monthDuration
                )
            }
            GradientButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.size16),
                title = buttonText,
                onClick = {
                    onAction(ActivationAction.ActivatePlan)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActivationView() {
    DatingAppTheme {
        ActivationView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewActivationView() {
    DatingAppTheme(darkTheme = true) {
        ActivationView()
    }
}