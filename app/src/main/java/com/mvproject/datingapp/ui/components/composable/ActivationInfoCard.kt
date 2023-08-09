/*
 * Create by Medvediev Viktor
 * last update: 09.08.23, 13:24
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.data.ActivationData.activationInfos
import com.mvproject.datingapp.data.model.ActivationInfo
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun ActivationInfoCard(
    modifier: Modifier = Modifier,
    activationInfo: ActivationInfo
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = activationInfo.logo),
            contentDescription = stringResource(id = activationInfo.title)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            text = stringResource(id = activationInfo.title),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge,
            fontSize = MaterialTheme.dimens.font24,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            text = stringResource(id = activationInfo.description),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActivationInfoCard() {
    DatingAppTheme {
        ActivationInfoCard(activationInfo = activationInfos.first())
    }
}