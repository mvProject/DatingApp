/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 10:40
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun DateSelector(
    modifier: Modifier = Modifier,
    initial: String = "",
    title: String = stringResource(id = R.string.scr_auth_date_select_title, "Test"),
    description: String = stringResource(id = R.string.scr_auth_date_select_description),
    onDateFilled: (String) -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        var dateValue by remember {
            mutableStateOf(initial)
        }

        DateTextField(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size32),
            dateText = dateValue,
            onDateTextChange = { value, dateFilled ->
                dateValue = value
                if (dateFilled) {
                    onDateFilled(dateValue)
                }
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size20),
            text = description,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeDateSelector() {
    DatingAppTheme {
        DateSelector(
            initial = "12"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewDateSelector() {
    DatingAppTheme(darkTheme = true) {
        DateSelector(
            initial = "12041985"
        )
    }
}