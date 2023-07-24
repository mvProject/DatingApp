/*
 * Create by Medvediev Viktor
 * last update: 27.06.23, 19:00
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.mvproject.datingapp.ui.components.message.ErrorMessage
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.isPasswordsValidAndConfirmed

@Composable
fun UpdatePasswordWithConfirmation(
    modifier: Modifier = Modifier,
    title: String = STRING_EMPTY,
    descriptionTop: String = STRING_EMPTY,
    descriptionBottom: String = STRING_EMPTY,
    verifyType: VerifyType = VerifyType.NONE,
    hintCurrent: String = stringResource(id = R.string.hint_password_current),
    hint: String = stringResource(id = R.string.hint_password_create),
    hintConfirm: String = stringResource(id = R.string.hint_password_confirm),
    btnTitle: String = stringResource(id = R.string.btn_title_save_password),
    onConfirmed: (String) -> Unit = {},
    onComplete: (String) -> Unit = {}
) {
    var enteredCurrent by remember {
        mutableStateOf(STRING_EMPTY)
    }

    var entered by remember {
        mutableStateOf(STRING_EMPTY)
    }

    var enteredConfirm by remember {
        mutableStateOf(STRING_EMPTY)
    }

    var isPasswordConfirmFailed by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(vertical = MaterialTheme.dimens.size8),
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
            hint = hintCurrent,
            onValueChange = { text ->
                enteredCurrent = text
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        InputPassword(
            modifier = Modifier.fillMaxWidth(),
            hint = hint,
            isErrorEntered = isPasswordConfirmFailed,
            onValueChange = { text ->
                entered = text
                isPasswordConfirmFailed = false
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        InputPassword(
            modifier = Modifier.fillMaxWidth(),
            hint = hintConfirm,
            isErrorEntered = isPasswordConfirmFailed,
            onValueChange = { text ->
                enteredConfirm = text
                isPasswordConfirmFailed = false
            }
        )

        if (isPasswordConfirmFailed) {
            ErrorMessage(
                text = stringResource(id = R.string.msg_error_pass_create_confirm)
            )
        }

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
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))
        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            title = btnTitle,
            onClick = {
                when (verifyType) {
                    VerifyType.PASSWORD -> {
                        if (isPasswordsValidAndConfirmed(entered, enteredConfirm)) {
                            onConfirmed(entered)
                        } else {
                            isPasswordConfirmFailed = true
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
fun PreviewUpdatePasswordWithConfirmation() {
    DatingAppTheme {
        UpdatePasswordWithConfirmation(
            descriptionTop = stringResource(id = R.string.scr_reset_password_description),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewUpdatePasswordWithConfirmation() {
    DatingAppTheme(darkTheme = true) {
        UpdatePasswordWithConfirmation(
            descriptionTop = stringResource(id = R.string.scr_reset_password_description),
        )
    }
}