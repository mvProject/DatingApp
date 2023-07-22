/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 15:05
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.btn_title_continue),
    btnColor: Color = MaterialTheme.colorScheme.secondary,
    titleColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = RoundedCornerShape(MaterialTheme.dimens.sizeZero),
    logo: Painter? = null,
    isBold: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = btnColor
        ),
        shape = shape
    ) {
        logo?.let { painter ->
            Icon(
                painter = painter,
                modifier = Modifier.size(MaterialTheme.dimens.size24),
                contentDescription = title,
                tint = titleColor
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))
        }

        val style = if (isBold)
            MaterialTheme.typography.titleSmall
        else
            MaterialTheme.typography.labelLarge
        Text(
            text = title,
            color = titleColor,
            style = style,
            //fontSize = MaterialTheme.dimens.font20
        )
    }
}