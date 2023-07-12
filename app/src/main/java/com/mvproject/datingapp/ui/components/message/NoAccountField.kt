/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 19:36
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.message

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun NoAccountField(
    modifier: Modifier = Modifier,
    title: String = "title",
    actionTitle: String = "actionTitle",
    onAction: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.dimens.size16,
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))
        Text(
            modifier = Modifier.clickable {
                onAction()
            },
            text = actionTitle,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}