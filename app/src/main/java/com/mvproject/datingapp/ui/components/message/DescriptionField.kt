/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 19:37
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.message

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun DescriptionField(
    modifier: Modifier = Modifier,
    description: String
) {
    Text(
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimens.size24),
        text = description,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelMedium
    )
}