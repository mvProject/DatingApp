/*
 * Create by Medvediev Viktor
 * last update: 11.07.23, 11:38
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.backColor
import com.mvproject.datingapp.utils.borderColor
import com.mvproject.datingapp.utils.textColor

@Composable
fun CheckSelector(
    text: String,
    logo: Painter? = null,
    isSelected: Boolean,
    onSelect: (String) -> Unit = {},
    onDeselect: (String) -> Unit = {}
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = {
                    if (isSelected) {
                        onDeselect(text)
                    } else {
                        onSelect(text)
                    }
                }
            )
            .background(
                color = backColor(isSelected),
                shape = MaterialTheme.shapes.large
            )
            .border(
                MaterialTheme.dimens.size1,
                color = borderColor(isSelected),
                shape = MaterialTheme.shapes.large
            )
            .padding(horizontal = MaterialTheme.dimens.size8),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        logo?.let { img ->
            Icon(
                modifier = Modifier
                    .padding(start = MaterialTheme.dimens.size8),
                painter = img,
                contentDescription = text,
                tint = textColor(isSelected)
            )
        }

        Text(
            modifier = Modifier
                .weight(WEIGHT_1)
                .padding(start = MaterialTheme.dimens.size12),
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = textColor(isSelected),
        )

        Checkbox(
            checked = isSelected,
            onCheckedChange = {
                if (isSelected) {
                    onDeselect(text)
                } else {
                    onSelect(text)
                }
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondaryContainer,
                checkmarkColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}