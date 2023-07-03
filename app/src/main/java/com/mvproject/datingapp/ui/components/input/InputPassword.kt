/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 18:59
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY

@Composable
fun InputPassword(
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.hint_password),
    onValueChange: (String) -> Unit = {}
) {
    var entered by remember {
        mutableStateOf(STRING_EMPTY)
    }

    var pswdVisibility by remember {
        mutableStateOf(false)
    }

    val icon = if (pswdVisibility) {
        painterResource(id = R.drawable.ic_pswd_visible)
    } else {
        painterResource(id = R.drawable.ic_pswd_invisible)
    }
    TextField(
        modifier = modifier,
        value = entered,
        onValueChange = {
            entered = it
            onValueChange(entered)
        },
        placeholder = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.dimens.size40),
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
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (pswdVisibility)
            VisualTransformation.None
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

@Preview(showBackground = true)
@Composable
fun PreviewCodeInputPassword() {
    DatingAppTheme {
        InputPassword()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewInputPassword() {
    DatingAppTheme(darkTheme = true) {
        InputPassword()
    }
}