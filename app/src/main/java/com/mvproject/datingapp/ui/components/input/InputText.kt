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
import com.mvproject.datingapp.data.enums.VerifyType
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.isValidEmail

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    initial: String = STRING_EMPTY,
    hint: String = stringResource(id = R.string.hint_email),
    verifyType: VerifyType = VerifyType.NONE,
    onValueChange: (String) -> Unit = {}
) {
    var entered by remember {
        mutableStateOf(initial)
    }
    val focusedTextColor = if (verifyType == VerifyType.EMAIL) {
        if (entered.isValidEmail())
            MaterialTheme.colorScheme.onPrimary
        else
            MaterialTheme.colorScheme.error
    } else MaterialTheme.colorScheme.onPrimary

    val unFocusedTextColor = if (verifyType == VerifyType.EMAIL) {
        if (entered.isValidEmail())
            MaterialTheme.colorScheme.onSurface
        else
            MaterialTheme.colorScheme.error
    } else MaterialTheme.colorScheme.onSurface

    TextField(
        modifier = modifier,
        value = entered,
        onValueChange = {
            entered = it
            onValueChange(entered)
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
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.colors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unFocusedTextColor,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeInputText() {
    DatingAppTheme {
        InputText()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewInputText() {
    DatingAppTheme(darkTheme = true) {
        InputText()
    }
}