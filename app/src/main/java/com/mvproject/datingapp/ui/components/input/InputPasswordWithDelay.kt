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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun InputPasswordWithDelay(
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.hint_password),
    hideDelay: Long,
    onValueChange: (String) -> Unit = {}
) {
    var entered by remember {
        mutableStateOf(STRING_EMPTY)
    }

    var pswdVisibility by remember {
        mutableStateOf(false)
    }

    var visibleChar by remember { mutableStateOf<Char?>(null) }
    var visibleCharIndex by remember { mutableStateOf(-1) }

    val coroutineScope = rememberCoroutineScope()

    fun hideVisibleChar() {
        visibleChar = null
        visibleCharIndex = -1
    }

    fun onPasswordChanged(newPassword: String) {
        entered = newPassword

        if (newPassword.isNotEmpty()) {
            val lastChar = newPassword.last()
            visibleChar = lastChar
            visibleCharIndex = newPassword.lastIndexOf(lastChar)

            coroutineScope.launch {
                delay(hideDelay)
                withContext(Dispatchers.Main) {
                    hideVisibleChar()
                }
            }
        } else {
            hideVisibleChar()
        }
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
            onPasswordChanged(it)
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
        else DelayedPasswordVisualTransformation(visibleChar, visibleCharIndex),
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

fun DelayedPasswordVisualTransformation(
    visibleChar: Char?,
    visibleCharIndex: Int
): VisualTransformation {
    return if (visibleChar != null) {
        VisualTransformation { text ->
            val transformedText = StringBuilder(text.text)
            if (visibleCharIndex != -1) {
                for (i in 0 until text.text.length) {
                    if (i != visibleCharIndex) {
                        transformedText.setCharAt(i, '\u2022')
                    }
                }
            }
            TransformedText(
                AnnotatedString(transformedText.toString()),
                OffsetMapping.Identity
            )
        }
    } else {
        PasswordVisualTransformation()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeInputPasswordWithDelay() {
    DatingAppTheme {
        InputPasswordWithDelay(hideDelay = 500)
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewInputPasswordWithDelay() {
    DatingAppTheme(darkTheme = true) {
        InputPasswordWithDelay(hideDelay = 500)
    }
}