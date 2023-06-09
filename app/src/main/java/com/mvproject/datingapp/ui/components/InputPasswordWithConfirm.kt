/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 11:23
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import timber.log.Timber

@Composable
fun InputPasswordWithConfirm(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String = "",
    hint: String = stringResource(id = R.string.hint_password_create),
    hintConfirm: String = stringResource(id = R.string.hint_password_confirm),
    onValueChange: (String) -> Unit = {}
) {
    var entered by remember {
        mutableStateOf("")
    }

    var enteredConfirm by remember {
        mutableStateOf("")
    }

    val isConfirmed by remember {
        derivedStateOf { entered == enteredConfirm }
    }

    var pswdVisibility by remember {
        mutableStateOf(false)
    }

    var pswdConfirmVisibility by remember {
        mutableStateOf(false)
    }

    val icon = if (pswdVisibility) {
        painterResource(id = R.drawable.ic_pswd_visible)
    } else {
        painterResource(id = R.drawable.ic_pswd_invisible)
    }

    val iconConfirm = if (pswdConfirmVisibility) {
        painterResource(id = R.drawable.ic_pswd_visible)
    } else {
        painterResource(id = R.drawable.ic_pswd_invisible)
    }


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayLarge
            )
        }

        if (description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size24),
                text = description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = entered,
            onValueChange = {
                entered = it
                Timber.w("testing entered:$entered, enteredConfirm:$enteredConfirm, enteredConfirm:$isConfirmed,")
                if (isConfirmed) {
                    onValueChange(entered)
                }
            },
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = hint,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    pswdVisibility = !pswdVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            visualTransformation = if (pswdVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary
            )
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = enteredConfirm,
            onValueChange = {
                enteredConfirm = it
                Timber.w("testing entered:$entered, enteredConfirm:$enteredConfirm, enteredConfirm:$isConfirmed,")
                if (isConfirmed) {
                    onValueChange(enteredConfirm)
                }
            },
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = hintConfirm,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    pswdConfirmVisibility = !pswdConfirmVisibility
                }) {
                    Icon(
                        painter = iconConfirm,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            visualTransformation = if (pswdConfirmVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeInputPasswordWithConfirm() {
    DatingAppTheme {
        InputPasswordWithConfirm(
            title = stringResource(id = R.string.scr_request_reset_password_title),
            description = stringResource(id = R.string.scr_request_reset_password_description),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewInputPasswordWithConfirm() {
    DatingAppTheme(darkTheme = true) {
        InputPasswordWithConfirm(
            title = stringResource(id = R.string.scr_request_reset_password_title),
            description = stringResource(id = R.string.scr_request_reset_password_description),
        )
    }
}