/*
 * Create by Medvediev Viktor
 * last update: 08.06.23, 20:19
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.bluevioletDark
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.ui.theme.hotpink

@Composable
fun ConfirmDialog(
    isDialogOpen: MutableState<Boolean>,
    title: String,
    description: String,
    btnText: String,
    image: Painter,
    onConfirm: () -> Unit = {}
) {
    if (isDialogOpen.value) {
        Dialog(
            onDismissRequest = { isDialogOpen.value = false }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(MaterialTheme.dimens.size6),
                shape = MaterialTheme.shapes.medium,
                shadowElevation = MaterialTheme.dimens.size8
            ) {
                Column(
                    modifier = Modifier
                        .padding(MaterialTheme.dimens.size24),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Image(
                        modifier = Modifier
                            .size(MaterialTheme.dimens.size128),
                        painter = image,
                        contentDescription = title
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.size32))

                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                    Text(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.dimens.size24),
                        text = description,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelMedium
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.dimens.size8),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(),
                        onClick = onConfirm
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    shape = MaterialTheme.shapes.large,
                                    brush = Brush.linearGradient(
                                        listOf(hotpink, bluevioletDark),
                                        start = Offset(0f, Float.POSITIVE_INFINITY),
                                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                                    )
                                )
                                .padding(
                                    horizontal = MaterialTheme.dimens.size4,
                                    vertical = MaterialTheme.dimens.size8
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = btnText,
                                color = MaterialTheme.colorScheme.onTertiary,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfirmDialog() {
    DatingAppTheme {
        val isDialogOpen = remember { mutableStateOf(true) }

        ConfirmDialog(
            isDialogOpen = isDialogOpen,
            title = stringResource(id = R.string.scr_reset_password_title),
            description = stringResource(id = R.string.scr_reset_password_description),
            btnText = stringResource(id = R.string.btn_title_sign_in),
            image = painterResource(id = R.drawable.ic_code_send)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewConfirmDialog() {
    DatingAppTheme(darkTheme = true) {
        val isDialogOpen = remember { mutableStateOf(true) }

        ConfirmDialog(
            isDialogOpen = isDialogOpen,
            title = stringResource(id = R.string.scr_reset_password_title),
            description = stringResource(id = R.string.scr_reset_password_description),
            btnText = stringResource(id = R.string.btn_title_sign_in),
            image = painterResource(id = R.drawable.ic_pswd_send)
        )
    }
}