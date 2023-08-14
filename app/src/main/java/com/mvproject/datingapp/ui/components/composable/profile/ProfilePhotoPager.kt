/*
 * Create by Medvediev Viktor
 * last update: 11.08.23, 12:25
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfilePhotoPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    userPhotos: List<String>
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageNestedScrollConnection = PagerDefaults
            .pageNestedScrollConnection(Orientation.Horizontal),
        pageContent = { position ->
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.medium
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = userPhotos[position],
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    )
}

