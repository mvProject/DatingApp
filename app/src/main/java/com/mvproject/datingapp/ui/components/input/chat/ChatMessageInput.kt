/*
 * Create by Medvediev Viktor
 * last update: 03.08.23, 19:08
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.ALPHA_40
import com.mvproject.datingapp.utils.ALPHA_60
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.isEmpty

@Composable
fun ChatMessageInput(
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
            textStyle = MaterialTheme.typography.labelLarge,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant)
        ) { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.outline.copy(alpha = ALPHA_40))
                    .border(
                        width = MaterialTheme.dimens.size1,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = ALPHA_60),
                        shape = CircleShape
                    )
                    .padding(
                        horizontal = MaterialTheme.dimens.size16,
                        vertical = MaterialTheme.dimens.size8
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
fun PreviewChatMessageInput() {
    DatingAppTheme {
        ChatMessageInput(
            hint = "hint"
        )
    }
}