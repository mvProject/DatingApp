/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 10:40
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.input.OtpTextField
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun CodeVerifier(
    modifier: Modifier = Modifier,
    email: String = "",
    title: String = stringResource(id = R.string.scr_code_verify_title),
    description: String = stringResource(id = R.string.scr_code_verify_code_send),
    onCodeFilled: (String) -> Unit = {}
) {
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

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        var otpValue by remember {
            mutableStateOf("")
        }

        OtpTextField(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size32),
            otpText = otpValue,
            onOtpTextChange = { value, otpInputFilled ->
                otpValue = value
                if (otpInputFilled) {
                    onCodeFilled(otpValue)
                }
            }
        )


        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size20),
            text = buildAnnotatedString {
                append(description)
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

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeCodeVerifier() {
    DatingAppTheme {
        CodeVerifier(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewCodeVerifier() {
    DatingAppTheme(darkTheme = true) {
        CodeVerifier(

        )
    }
}