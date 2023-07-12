/*
 * Create by Medvediev Viktor
 * last update: 28.06.23, 10:31
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.otpCode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.state.TimerState
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.dialog.InfoDialog
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.STRING_SPACE
import timber.log.Timber

@Composable
fun CodeVerifier(
    modifier: Modifier = Modifier,
    timerState: TimerState = TimerState(),
    email: String = STRING_EMPTY,
    code: String = STRING_EMPTY,
    isEmailChangeEnabled: Boolean = true,
    title: String = stringResource(id = R.string.scr_code_verify_title),
    descriptionTop: String = stringResource(id = R.string.scr_code_verify_code_send),
    descriptionBottom: String = stringResource(id = R.string.scr_code_verify_code_receive),
    onChangeEmail: () -> Unit = {},
    onRetry: () -> Unit = {},
    onCodeVerify: () -> Unit = {}
) {
    val isVerificationCodeFailedDialogOpen = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxSize()
            .padding(vertical = MaterialTheme.dimens.size12),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        var otpValue by remember {
            mutableStateOf(STRING_EMPTY)
        }

        var otpFilled by remember {
            mutableStateOf(false)
        }

        OtpTextField(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size32),
            otpText = otpValue,
            onOtpTextChange = { value, otpInputFilled ->
                otpValue = value
                otpFilled = otpInputFilled
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size10),
            text = buildAnnotatedString {
                append(descriptionTop)
                append("\n")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
                    )
                ) {
                    append(email)
                }
            },
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )

        if (isEmailChangeEnabled) {
            Text(
                modifier = Modifier
                    .clickable {
                        onChangeEmail()
                    }
                    .padding(horizontal = MaterialTheme.dimens.size24),
                text = stringResource(id = R.string.scr_code_verify_change_email),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        val textColor = if (timerState.isTimerEnabled) {
            MaterialTheme.colorScheme.outline
        } else {
            MaterialTheme.colorScheme.secondary
        }
        Text(
            modifier = Modifier
                .clickable {
                    if (!timerState.isTimerEnabled) {
                        otpValue = STRING_EMPTY
                        onRetry()
                    }
                }
                .padding(horizontal = MaterialTheme.dimens.size24),
            text = stringResource(id = R.string.scr_code_verify_code_send_retry),
            color = textColor,
            style = MaterialTheme.typography.headlineSmall
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(id = R.string.scr_code_verify_code_receive_timer),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size2))
            Text(
                modifier = Modifier.width(MaterialTheme.dimens.size60),
                text = stringResource(
                    id = R.string.scr_code_verify_code_receive_value,
                    timerState.timeLeft
                ),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size10),
            text = buildAnnotatedString {
                append(descriptionBottom)
                append(STRING_SPACE)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                        fontSize = MaterialTheme.dimens.font12
                    )
                ) {
                    append(email)
                }
            },
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelSmall,
            fontSize = MaterialTheme.dimens.font12,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        GradientButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                Timber.i("entered:$otpValue, target:$code")
                if (otpFilled && otpValue == code) {
                    onCodeVerify()
                } else {
                    isVerificationCodeFailedDialogOpen.value = true
                }
            }
        )
    }

    InfoDialog(
        isDialogOpen = isVerificationCodeFailedDialogOpen,
        title = stringResource(id = R.string.dlg_code_validation_error_title),
        description = stringResource(id = R.string.dlg_code_validation_error_description),
        onConfirm = {
            isVerificationCodeFailedDialogOpen.value = false
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeCodeVerifier() {
    DatingAppTheme {
        CodeVerifier(
            email = "test@test.com"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewCodeVerifier() {
    DatingAppTheme(darkTheme = true) {
        CodeVerifier(
            email = "test@test.com",
        )
    }
}