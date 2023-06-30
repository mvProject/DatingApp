/*
 * Create by Medvediev Viktor
 * last update: 27.06.23, 19:00
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.VerifyType
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.isPasswordsValidAndConfirmed

@Composable
fun SetPasswordWithConfirmation(
    modifier: Modifier = Modifier,
    title: String = STRING_EMPTY,
    descriptionTop: String = STRING_EMPTY,
    descriptionBottom: String = STRING_EMPTY,
    verifyType: VerifyType = VerifyType.NONE,
    hint: String = stringResource(id = R.string.hint_password_create),
    hintConfirm: String = stringResource(id = R.string.hint_password_confirm),
    onConfirmed: (String) -> Unit = {},
    onComplete: (String) -> Unit = {},
    onFailed: () -> Unit = {}
) {
    var entered by remember {
        mutableStateOf(STRING_EMPTY)
    }

    var enteredConfirm by remember {
        mutableStateOf(STRING_EMPTY)
    }

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxSize()
            .padding(vertical = MaterialTheme.dimens.size12),
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

        if (descriptionTop.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = descriptionTop,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        InputPassword(
            modifier = Modifier.fillMaxWidth(),
            hint = hint,
            onValueChange = { text ->
                entered = text
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        InputPassword(
            modifier = Modifier.fillMaxWidth(),
            hint = hintConfirm,
            onValueChange = { text ->
                enteredConfirm = text
            }
        )

        if (descriptionBottom.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.size4),
                text = descriptionBottom,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(WEIGHT_1))

        GradientButton(
            modifier = Modifier
                .fillMaxWidth(),
            title = stringResource(id = R.string.btn_title_save_password),
            onClick = {
                when (verifyType) {
                    VerifyType.PASSWORD -> {
                        if (isPasswordsValidAndConfirmed(entered, enteredConfirm)) {
                            onConfirmed(entered)
                        } else {
                            onFailed()
                        }
                    }

                    else -> onComplete(entered)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeSetPasswordWithConfirmation() {
    DatingAppTheme {
        SetPasswordWithConfirmation(
            title = stringResource(id = R.string.scr_request_reset_password_title),
            descriptionTop = stringResource(id = R.string.scr_request_reset_password_description),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewSetPasswordWithConfirmation() {
    DatingAppTheme(darkTheme = true) {
        SetPasswordWithConfirmation(
            title = stringResource(id = R.string.scr_request_reset_password_title),
            descriptionTop = stringResource(id = R.string.scr_request_reset_password_description),
        )
    }
}