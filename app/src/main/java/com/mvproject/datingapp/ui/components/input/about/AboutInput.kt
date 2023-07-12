/*
 * Create by Medvediev Viktor
 * last update: 10.07.23, 17:15
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.input.InputLimitedText
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.ABOUT_MAX_LENGTH
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun AboutInput(
    modifier: Modifier = Modifier,
    logo: Int,
    title: String = STRING_EMPTY,
    onAboutEntered: (String) -> Unit = {}
) {
    var entered by remember {
        mutableStateOf(STRING_EMPTY)
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(MaterialTheme.dimens.size200),
            painter = rememberAsyncImagePainter(model = logo),
            contentDescription = title
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = MaterialTheme.dimens.font22
        )

        InputLimitedText(
            initial = entered,
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_about),
            maxLength = ABOUT_MAX_LENGTH,
            onValueChange = { text ->
                entered = text
            }
        )

        Spacer(modifier = Modifier.weight(WEIGHT_1))

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.btn_title_next),
            onClick = {
                onAboutEntered(entered)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeAboutInput() {
    DatingAppTheme {
        AboutInput(logo = R.drawable.questions_about)
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewAboutInput() {
    DatingAppTheme(darkTheme = true) {
        AboutInput(logo = R.drawable.questions_about)
    }
}