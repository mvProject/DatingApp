/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 15:05
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun FacebookButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary
        ),
        shape = MaterialTheme.shapes.large,
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.dimens.size10,
            vertical = MaterialTheme.dimens.size12
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_facebook),
            modifier = Modifier.size(MaterialTheme.dimens.size24),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.btn_title_sign_facebook),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFacebookButton() {
    DatingAppTheme {
        FacebookButton()
    }
}