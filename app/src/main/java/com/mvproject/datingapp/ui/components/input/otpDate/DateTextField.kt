/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 19:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.otpDate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

@Composable
fun DateTextField(
    modifier: Modifier = Modifier,
    dateText: String,
    dateCount: Int = 8,
    onDateTextChange: (String, Boolean) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        if (dateText.length > dateCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $dateCount characters")
        }
        focusRequester.requestFocus()
    }

    var dateFilled by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(dateFilled) {
        if (dateFilled) {
            focusManager.clearFocus()
        }
    }

    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester),
        value = TextFieldValue(
            dateText,
            selection = TextRange(dateText.length)
        ),
        onValueChange = {
            if (it.text.isDigitsOnly()) {
                if (it.text.length <= dateCount) {
                    dateFilled = it.text.length == dateCount
                    onDateTextChange.invoke(it.text, dateFilled)
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(dateCount) { index ->
                    CharView(
                        index = index,
                        text = dateText
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.dimens.size4))
                    if (index == 1 || index == 3) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = MaterialTheme.dimens.size2),
                            text = "/",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
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
        index >= text.length -> {
            when (index) {
                in 0..1 -> "D"
                in 2..3 -> "M"
                else -> "Y"
            }
        }

        else -> text[index].toString()
    }

    val color = when {
        index >= text.length -> MaterialTheme.colorScheme.onSurface
        else -> MaterialTheme.colorScheme.onPrimary
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .width(MaterialTheme.dimens.size32)
                .padding(MaterialTheme.dimens.size2),
            text = char,
            style = MaterialTheme.typography.bodyLarge,
            color = color,
            textAlign = TextAlign.Center
        )

        Divider(
            Modifier
                .width(MaterialTheme.dimens.size18)
                .padding(bottom = MaterialTheme.dimens.size2),
            color = when {
                isFocused -> MaterialTheme.colorScheme.secondaryContainer
                else -> MaterialTheme.colorScheme.onSurface
            },
            thickness = MaterialTheme.dimens.size1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeDateTextField() {
    DatingAppTheme {
        DateTextField(
            dateText = "1",
            onDateTextChange = { s, d ->

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewDateTextField() {
    DatingAppTheme(darkTheme = true) {
        DateTextField(
            dateText = "123",
            onDateTextChange = { s, d ->

            }
        )
    }
}