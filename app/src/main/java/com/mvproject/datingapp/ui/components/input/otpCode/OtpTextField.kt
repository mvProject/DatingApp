/*
 * Create by Medvediev Viktor
 * last update: 27.06.23, 19:48
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.otpCode

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.DEFAULT_CODE_LENGTH
import com.mvproject.datingapp.utils.STRING_EMPTY

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = DEFAULT_CODE_LENGTH,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
        focusRequester.requestFocus()
    }

    var otpFilled by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(otpFilled) {
        if (otpFilled) {
            focusManager.clearFocus()
        }
    }

    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester),
        value = TextFieldValue(
            otpText,
            selection = TextRange(otpText.length)
        ),
        onValueChange = {
            if (it.text.isDigitsOnly()) {
                if (it.text.length <= otpCount) {
                    otpFilled = it.text.length == otpCount
                    onOtpTextChange.invoke(it.text, otpFilled)
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> STRING_EMPTY
        index > text.length -> STRING_EMPTY
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(MaterialTheme.dimens.size34)
            .border(
                MaterialTheme.dimens.size1,
                color = when {
                    isFocused -> MaterialTheme.colorScheme.onSurface
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
                shape = MaterialTheme.shapes.small
            )
            .padding(MaterialTheme.dimens.size2),
        text = char,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeOtpTextField() {
    DatingAppTheme {
        OtpTextField(
            otpText = "test",
            onOtpTextChange = { s, d ->

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewOtpTextField() {
    DatingAppTheme(darkTheme = true) {
        OtpTextField(
            otpText = "test",
            onOtpTextChange = { s, d ->

            }
        )
    }
}