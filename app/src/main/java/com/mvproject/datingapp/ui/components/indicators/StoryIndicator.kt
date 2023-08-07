/*
 * Create by Medvediev Viktor
 * last update: 26.07.23, 14:10
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.ALPHA_25
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun StoryIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.size4)
    ) {
        for (i in INT_ZERO..totalDots) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.size2)
                    .weight(WEIGHT_1)
                    .padding(horizontal = MaterialTheme.dimens.size2)
                    .background(
                        color = if (i == selectedIndex)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.primary.copy(alpha = ALPHA_25),
                        shape = RoundedCornerShape(MaterialTheme.dimens.size100)
                    )
            )
        }
    }
}