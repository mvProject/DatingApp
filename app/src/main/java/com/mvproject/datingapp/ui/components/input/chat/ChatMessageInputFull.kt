/*
 * Create by Medvediev Viktor
 * last update: 03.08.23, 19:05
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatMessageInputFull(
    modifier: Modifier = Modifier,
    initial: String = STRING_EMPTY,
    hint: String = stringResource(id = R.string.hint_message),
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    onSendMessage: (String) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        var entered by remember {
            mutableStateOf(initial)
        }

        val interactionSource = remember { MutableInteractionSource() }

        BasicTextField(
            modifier = Modifier.weight(WEIGHT_1),
            value = entered,
            onValueChange = {
                entered = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Default,
                keyboardType = KeyboardType.Password
            ),
            singleLine = singleLine,
            enabled = enabled,
            interactionSource = interactionSource,
            visualTransformation = visualTransformation,
            textStyle = MaterialTheme.typography.labelLarge,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant)
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = entered,
                innerTextField = innerTextField,
                visualTransformation = visualTransformation,
                singleLine = singleLine,
                enabled = enabled,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.dimens.size16,
                    vertical = MaterialTheme.dimens.size8
                ),
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = hint,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                },
                colors = colors,
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled,
                        isError,
                        interactionSource,
                        colors,
                        CircleShape
                    )
                }
            )
        }

        Spacer(Modifier.width(MaterialTheme.dimens.size8))

        IconButton(
            onClick = {
                onSendMessage(entered)
                entered = STRING_EMPTY
            },
            enabled = entered.isNotBlank(),
        ) {
            Image(
                painterResource(id = R.drawable.ic_send_message),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatMessageInputFull() {
    DatingAppTheme {
        ChatMessageInputFull(
            hint = "hint"
        )
    }
}