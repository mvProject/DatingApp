/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 15:05
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.bluevioletDark
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.ui.theme.hotpink
import com.mvproject.datingapp.utils.gradientBackground

@Composable
fun GradientSmallButton(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.btn_title_continue),
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        enabled = enabled,
        contentPadding = PaddingValues(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .gradientBackground(listOf(hotpink, bluevioletDark), 45f)
                .clip(MaterialTheme.shapes.large)
                .padding(
                    horizontal = MaterialTheme.dimens.size4,
                    vertical = MaterialTheme.dimens.size8
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGradientSmallButton() {
    DatingAppTheme {
        GradientSmallButton(modifier = Modifier.fillMaxWidth())
    }
}