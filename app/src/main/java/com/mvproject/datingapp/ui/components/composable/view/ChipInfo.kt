/*
 * Create by Medvediev Viktor
 * last update: 11.07.23, 11:38
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun ChipInfo(
    text: String,
    logo: Painter? = null,
) {
    Row(
        Modifier
            .wrapContentSize()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large
            )
            .border(
                MaterialTheme.dimens.size1,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.large
            )
            .padding(
                vertical = MaterialTheme.dimens.size4,
                horizontal = MaterialTheme.dimens.size8
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        logo?.let { img ->
            Image(
                painter = img,
                contentDescription = text
            )
        }

        Text(
            modifier = Modifier
                .padding(start = MaterialTheme.dimens.size8),
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}