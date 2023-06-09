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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    title: String = "",
    descriptionBefore: String = "",
    descriptionAfter: String = "",
    hint: String = stringResource(id = R.string.hint_email),
    onValueChange: (String) -> Unit = {}
) {
    var entered by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge
        )

        if (descriptionBefore.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size24),
                text = descriptionBefore,
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
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary
            )
        )

        if (descriptionAfter.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size24),
                text = descriptionAfter,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeInputText() {
    DatingAppTheme {
        InputText(
            title = stringResource(id = R.string.scr_request_reset_password_title),
            descriptionBefore = stringResource(id = R.string.scr_request_reset_password_description),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewInputText() {
    DatingAppTheme(darkTheme = true) {
        InputText(
            title = stringResource(id = R.string.scr_request_reset_password_title),
            descriptionAfter = stringResource(id = R.string.scr_request_reset_password_description),
        )
    }
}