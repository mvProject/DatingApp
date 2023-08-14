/*
 * Create by Medvediev Viktor
 * last update: 03.08.23, 19:08
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.isEmpty

@Composable
fun MatchMessageInput(
    modifier: Modifier = Modifier,
    initial: String = STRING_EMPTY,
    hint: String = stringResource(id = R.string.hint_message),
    onSendMessage: (String) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        var entered by remember {
            mutableStateOf(initial)
        }

        Box(
            modifier = modifier
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                value = entered,
                onValueChange = {
                    entered = it
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Default,
                    keyboardType = KeyboardType.Password
                ),
                textStyle = MaterialTheme.typography.labelLarge,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant)
            ) { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .clip(CircleShape)
                        .padding(
                            start = MaterialTheme.dimens.size16,
                            end = MaterialTheme.dimens.size88,
                            top = MaterialTheme.dimens.size8,
                            bottom = MaterialTheme.dimens.size8,
                        )
                ) {
                    if (entered.isEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = hint,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }

                    innerTextField()
                }
            }

            GradientButton(
                modifier = Modifier
                    .width(MaterialTheme.dimens.size88)
                    .align(Alignment.CenterEnd),
                title = stringResource(id = R.string.btn_title_send),
                enabled = entered.isNotBlank(),
                onClick = {
                    onSendMessage(entered)
                    entered = STRING_EMPTY
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMatchMessageInput() {
    DatingAppTheme {
        MatchMessageInput(
            hint = "hint"
        )
    }
}