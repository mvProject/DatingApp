/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 15:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.ANIM_DURATION_500

@Composable
fun BottomDialog(
    modifier: Modifier = Modifier,
    isVisible: MutableState<Boolean>,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(ANIM_DURATION_500)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(ANIM_DURATION_500)
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clickable {
                    isVisible.value = false
                }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(
                    topStart = MaterialTheme.dimens.size16,
                    topEnd = MaterialTheme.dimens.size16
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = MaterialTheme.dimens.size8
                ),
                border = BorderStroke(
                    width = MaterialTheme.dimens.size1,
                    color = MaterialTheme.colorScheme.outline
                )
            ) {
                content()
            }
        }
    }
}