/*
 * Create by Medvediev Viktor
 * last update: 27.06.23, 18:59
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
import androidx.compose.runtime.derivedStateOf
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
import com.mvproject.datingapp.ui.components.message.PrivacyField
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.isEmpty
import com.mvproject.datingapp.utils.isValidEmail

@Composable
fun InputTextWithDescription(
    modifier: Modifier = Modifier,
    initial: String = STRING_EMPTY,
    title: String = STRING_EMPTY,
    verifyType: VerifyType = VerifyType.NONE,
    descriptionBottom: String = STRING_EMPTY,
    descriptionTop: String = STRING_EMPTY,
    privacyText: String = STRING_EMPTY,
    btnTitle: String = stringResource(id = R.string.btn_title_continue),
    maxLength: Int = INT_ZERO,
    hint: String = stringResource(id = R.string.hint_email),
    onSendClick: (String) -> Unit = {}
) {
    var entered by remember {
        mutableStateOf(initial)
    }

    var showNotVerifiedError by remember {
        mutableStateOf(false)
    }

    var showEmptyError by remember {
        mutableStateOf(false)
    }

    val isVerificationFailed by remember {
        derivedStateOf {
            showNotVerifiedError || showEmptyError
        }
    }

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxSize()
            .padding(vertical = MaterialTheme.dimens.size12),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge
        )

        if (descriptionTop.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size24),
                text = descriptionTop,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        InputText(
            initial = entered,
            modifier = Modifier.fillMaxWidth(),
            verifyType = verifyType,
            hint = hint,
            isErrorEntered = isVerificationFailed,
            maxLength = maxLength,
            onValueChange = { text ->
                showNotVerifiedError = false
                showEmptyError = false
                entered = text
            }
        )

        if (showNotVerifiedError) {
            ErrorMessage(
                text = stringResource(id = R.string.msg_error_mail_validation)
            )
        }

        if (showEmptyError) {
            ErrorMessage(
                text = stringResource(id = R.string.msg_error_name_empty)
            )
        }

        if (descriptionBottom.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.dimens.size24),
                text = descriptionBottom,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (privacyText.isNotEmpty()) {
            PrivacyField(
                text = privacyText
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))
        }

        GradientButton(
            modifier = Modifier
                .fillMaxWidth(),
            title = btnTitle,
            onClick = {
                when (verifyType) {
                    VerifyType.EMAIL -> {
                        showNotVerifiedError = !entered.isValidEmail()
                        if (entered.isValidEmail()) {
                            onSendClick(entered)
                        }
                    }

                    VerifyType.NAME -> {
                        showEmptyError = entered.isEmpty()
                        if (entered.isNotEmpty()) {
                            onSendClick(entered)
                        }
                    }

                    else -> onSendClick(entered)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeInputTextWithDescription() {
    DatingAppTheme {
        InputTextWithDescription(
            title = stringResource(id = R.string.scr_request_reset_password_title),
            descriptionTop = stringResource(id = R.string.scr_request_reset_password_description),
        )
    }
}