/*
 * Create by Medvediev Viktor
 * last update: 14.08.23, 11:08
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.candidate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CandidatePhotoPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    userPhotos: List<Int>
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageContent = { position ->
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.medium
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium),
                    painter = painterResource(id = userPhotos[position]),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    )
}