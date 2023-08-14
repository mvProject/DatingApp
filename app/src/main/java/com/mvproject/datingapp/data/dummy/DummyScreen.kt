/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 14:39
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.dummy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun DummyScreen(
    title: String,
    buttonFirstTitle: String = "",
    buttonFirstAction: () -> Unit = {},
    buttonSecondTitle: String = "",
    buttonSecondAction: () -> Unit = {},
    buttonThirdTitle: String = "",
    buttonThirdAction: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.inversePrimary,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size12))

        if (buttonFirstTitle.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size12))
            GradientButton(
                modifier = Modifier.fillMaxWidth(),
                title = buttonFirstTitle,
                onClick = buttonFirstAction
            )
        }
        if (buttonSecondTitle.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size12))
            GradientButton(
                modifier = Modifier.fillMaxWidth(),
                title = buttonSecondTitle,
                onClick = buttonSecondAction
            )
        }

        if (buttonThirdTitle.isNotEmpty()) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size12))
            GradientButton(
                modifier = Modifier.fillMaxWidth(),
                title = buttonThirdTitle,
                onClick = buttonThirdAction
            )
        }
    }
}
