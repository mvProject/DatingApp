/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 15:05
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    btnColor: Color = MaterialTheme.colorScheme.secondary,
    titleColor: Color = MaterialTheme.colorScheme.primary,
    logo: Painter? = null,
    shape: Shape = MaterialTheme.shapes.large,
    onClick: () -> Unit = {}
) {

    Button(
        modifier = modifier,
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
                contentDescription = null,
                tint = titleColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewImageButton() {
    DatingAppTheme {
        ImageButton(
            logo = painterResource(id = R.drawable.ic_profile_like)
        )
    }
}