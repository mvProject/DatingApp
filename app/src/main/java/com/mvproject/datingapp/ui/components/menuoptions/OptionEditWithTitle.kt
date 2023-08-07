/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 16:23
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.menuoptions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun OptionEditWithTitle(
    modifier: Modifier = Modifier,
    title: String = STRING_EMPTY,
    selected: String = STRING_EMPTY,
    onChange: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                onChange()
            }
            .padding(MaterialTheme.dimens.size16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(WEIGHT_1)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.dimens.font14
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = selected,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.width(MaterialTheme.dimens.size16))

        Icon(
            painter = painterResource(id = R.drawable.ic_right),
            contentDescription = title,
            tint = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.6f)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOptionEditWithTitle() {
    DatingAppTheme {
        OptionEditWithTitle(
            title = "About me",
            selected = "I'm looking for a nice girl to meet. I am sociable, I like sports, computer games, traveling. If we have a lot in common, swipe right"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewOptionEditWithTitle() {
    DatingAppTheme(darkTheme = true) {
        OptionEditWithTitle(
            title = "Work",
            selected = "Designer in company KinectPro"
        )
    }
}