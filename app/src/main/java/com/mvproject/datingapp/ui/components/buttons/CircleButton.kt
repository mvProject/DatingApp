/*
 * Create by Medvediev Viktor
 * last update: 24.07.23, 18:27
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    logo: Painter? = null,
    icon: ImageVector? = null,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary
) {
    IconButton(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .size(MaterialTheme.dimens.size60)
            .border(MaterialTheme.dimens.size2, backgroundColor, CircleShape),
        onClick = onClick
    ) {
        logo?.let {
            Image(logo, null)
        }
        icon?.let {
            Icon(
                icon, null,
                tint = iconColor
            )
        }
    }
}