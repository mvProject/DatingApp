/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 16:23
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.menuoptions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun OptionSettings(
    modifier: Modifier = Modifier,
    title: String = STRING_EMPTY,
    titleColor: Color = MaterialTheme.colorScheme.onPrimary,
    selected: String = STRING_EMPTY,
    onChange: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clickable {
                onChange()
            }
            .padding(start = MaterialTheme.dimens.size16)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.dimens.size10),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = titleColor
            )
            Spacer(modifier = Modifier.weight(WEIGHT_1))
            Text(
                text = selected,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size4))
            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f)
            )
        }
        Divider(
            color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOptionSettings() {
    DatingAppTheme {
        OptionSettings(
            title = "Title",
            selected = "Not Set"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewOptionSettings() {
    DatingAppTheme(darkTheme = true) {
        OptionSettings(
            title = "Title",
            selected = "Not Set"
        )
    }
}