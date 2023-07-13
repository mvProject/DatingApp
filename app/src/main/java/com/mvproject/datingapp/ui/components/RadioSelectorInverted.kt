/*
 * Create by Medvediev Viktor
 * last update: 11.07.23, 11:39
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.textColor

@Composable
fun RadioSelectorInverted(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onSelect: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .selectable(
                selected = isSelected,
                onClick = { onSelect(text) }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround

    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelect(text) },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )

        Text(
            modifier = Modifier
                .weight(WEIGHT_1)
                .padding(start = MaterialTheme.dimens.size12),
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = textColor(isSelected),
        )
    }
}