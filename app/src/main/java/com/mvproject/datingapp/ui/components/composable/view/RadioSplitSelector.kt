/*
 * Create by Medvediev Viktor
 * last update: 11.07.23, 11:39
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.STRING_SPACE
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.backColor
import com.mvproject.datingapp.utils.borderColor
import com.mvproject.datingapp.utils.textColor
import com.mvproject.datingapp.utils.textSplitColor

@Composable
fun RadioSplitSelector(
    textBefore: String,
    textAfter: String = STRING_EMPTY,
    logo: Painter? = null,
    isSelected: Boolean,
    onSelect: (String) -> Unit = {}
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = {
                    onSelect(textBefore)
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
                contentDescription = textBefore,
                tint = textColor(isSelected)
            )
        }

        Text(
            modifier = Modifier
                .weight(WEIGHT_1)
                .padding(start = MaterialTheme.dimens.size12),
            text = buildAnnotatedString {
                append(textBefore)
                if (textAfter.isNotEmpty()) {
                    append(STRING_SPACE)
                    withStyle(
                        style = SpanStyle(
                            color = textSplitColor(isSelected),
                        )
                    ) {
                        append(textAfter)
                    }
                }
            },
            style = MaterialTheme.typography.labelLarge,
            color = textColor(isSelected),
        )

        RadioButton(
            selected = isSelected,
            onClick = {
                onSelect(textBefore)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )
    }
}