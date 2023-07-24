/*
 * Create by Medvediev Viktor
 * last update: 10.07.23, 17:15
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.work

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
import com.mvproject.datingapp.data.model.UserWork
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.input.InputText
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.INFO_MAX_LENGTH
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun WorkInput(
    modifier: Modifier = Modifier,
    logo: Int,
    title: String = STRING_EMPTY,
    onWorkEntered: (UserWork) -> Unit = {}
) {
    var enteredJob by remember {
        mutableStateOf(STRING_EMPTY)
    }

    var enteredCompany by remember {
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

        InputText(
            initial = enteredJob,
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_job_title),
            hintAlign = null,
            maxLength = INFO_MAX_LENGTH,
            onValueChange = { text ->
                enteredJob = text
            }
        )

        InputText(
            initial = enteredCompany,
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(id = R.string.hint_company),
            hintAlign = null,
            maxLength = INFO_MAX_LENGTH,
            onValueChange = { text ->
                enteredCompany = text
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Spacer(modifier = Modifier.weight(WEIGHT_1))

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.btn_title_next),
            onClick = {
                onWorkEntered(
                    UserWork(
                        jobTitle = enteredJob,
                        jobCompany = enteredCompany
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkInput() {
    DatingAppTheme {
        WorkInput(logo = R.drawable.questions_work)
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewWorkInput() {
    DatingAppTheme(darkTheme = true) {
        WorkInput(logo = R.drawable.questions_work)
    }
}