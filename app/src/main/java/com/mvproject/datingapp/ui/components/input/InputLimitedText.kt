/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 18:58
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY

@Composable
fun InputLimitedText(
    modifier: Modifier = Modifier,
    initial: String = STRING_EMPTY,
    hint: String = stringResource(id = R.string.hint_email),
    maxLength: Int = INT_ZERO,
    onValueChange: (String) -> Unit = {}
) {
    var entered by remember {
        mutableStateOf(initial)
    }

    val left by remember {
        derivedStateOf {
            maxLength - entered.length
        }
    }

    TextField(
        modifier = modifier,
        value = entered,
        onValueChange = {
            if (maxLength > INT_ZERO) {
                if (it.length <= maxLength) {
                    entered = it
                    onValueChange(entered)
                }
            } else {
                entered = it
                onValueChange(entered)
            }
        },
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = hint,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Default,
            keyboardType = KeyboardType.Password
        ),
        supportingText = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.hint_characters, left),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.End,
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeInputLimitedText() {
    DatingAppTheme {
        InputLimitedText()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewInputLimitedText() {
    DatingAppTheme(darkTheme = true) {
        InputLimitedText()
    }
}