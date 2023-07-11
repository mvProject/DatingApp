/*
 * Create by Medvediev Viktor
 * last update: 16.06.23, 16:56
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.dimens.size4),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_warning),
            contentDescription = text
        )

        Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))

        Text(
            modifier = Modifier
                .weight(1f),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.error,
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeErrorMessage() {
    DatingAppTheme {
        ErrorMessage(
            text = stringResource(id = R.string.scr_auth_privacy_email),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewErrorMessage() {
    DatingAppTheme(darkTheme = true) {
        ErrorMessage(
            text = stringResource(id = R.string.scr_auth_privacy_email),
        )
    }
}