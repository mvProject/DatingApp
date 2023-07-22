/*
 * Create by Medvediev Viktor
 * last update: 19.07.23, 18:15
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun StaticGrid(
    columns: Int,
    itemCount: Int,
    modifier: Modifier = Modifier,
    content: @Composable() (Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(MaterialTheme.dimens.size16),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size8)
    ) {
        var rows = (itemCount / columns)
        if (itemCount.mod(columns) > 0) {
            rows += 1
        }

        for (rowId in 0 until rows) {
            val firstIndex = rowId * columns

            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size8)
            ) {
                for (columnId in 0 until columns) {
                    val index = firstIndex + columnId
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        if (index < itemCount) {
                            content(index)
                        }
                    }
                }
            }
        }
    }
}