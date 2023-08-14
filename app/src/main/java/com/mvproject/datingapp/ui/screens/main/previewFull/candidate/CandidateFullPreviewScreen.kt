/*
 * Create by Medvediev Viktor
 * last update: 14.08.23, 10:14
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.previewFull.candidate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.composable.candidate.CandidateCharacteristicsView
import com.mvproject.datingapp.ui.components.composable.candidate.CandidatePhotoPager
import com.mvproject.datingapp.ui.components.indicators.StoryIndicator
import com.mvproject.datingapp.ui.screens.main.previewFull.candidate.state.CandidateFullPreviewState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.FLOAT_ZERO
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STEP_1

@Composable
fun CandidateFullPreviewScreen(
    viewModel: CandidateFullPreviewViewModel,
    onNavigationBack: () -> Unit = {}
) {
    val candidatePreviewState by viewModel.candidatePreviewState.collectAsStateWithLifecycle()

    CandidateFullPreviewView(
        state = candidatePreviewState,
        onBackClick = onNavigationBack
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CandidateFullPreviewView(
    state: CandidateFullPreviewState = CandidateFullPreviewState(),
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = MaterialTheme.dimens.size8,
                vertical = MaterialTheme.dimens.size16
            )
    ) {
        val pages by remember {
            mutableStateOf(state.previewUser.photos)
        }

        val pagerState = rememberPagerState(
            initialPage = INT_ZERO,
            initialPageOffsetFraction = FLOAT_ZERO
        ) {
            pages.size
        }
        Box(
            modifier = Modifier
                .height(MaterialTheme.dimens.size420)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        ) {

            CandidatePhotoPager(
                modifier = Modifier.fillMaxWidth(),
                pagerState = pagerState,
                userPhotos = pages
            )

            StoryIndicator(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimens.size8)
                    .align(Alignment.TopStart),
                totalDots = pages.size - STEP_1,
                selectedIndex = pagerState.currentPage
            )

            Icon(
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.dimens.size16,
                        end = MaterialTheme.dimens.size8
                    )
                    .size(MaterialTheme.dimens.size40)
                    .clickable {
                        onBackClick()
                    }
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        CandidateCharacteristicsView(
            previewUser = state.previewUser
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCandidateFullPreviewView() {
    DatingAppTheme {
        CandidateFullPreviewView()
    }
}
