/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 18:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.profile.ProfileInterest
import com.mvproject.datingapp.ui.components.RadioSelector
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun InterestSelector(
    modifier: Modifier = Modifier,
    selectedOption: String = stringResource(ProfileInterest.INTEREST_LOVE.title),
    radioOptions: List<ProfileInterest> = ProfileInterest.values().toList(),
    title: String = stringResource(id = R.string.scr_auth_interest_select_title),
    btnTitle: String = stringResource(id = R.string.btn_title_continue),
    onOptionSelected: (ProfileInterest) -> Unit = {}
) {
    val context = LocalContext.current
    var selected by remember {
        mutableStateOf(selectedOption)
    }
    val selectedInterest by remember {
        derivedStateOf {
            ProfileInterest.values().first {
                context.getText(it.title) == selected
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxSize()
            .padding(
                vertical = MaterialTheme.dimens.size12,
                horizontal = MaterialTheme.dimens.size24
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = modifier
                .padding(horizontal = MaterialTheme.dimens.size24),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        Column {
            radioOptions.forEach { option ->
                val text = stringResource(id = option.title)
                val isSelected = text == selected

                RadioSelector(
                    text = text,
                    logo = painterResource(id = option.logo),
                    isSelected = isSelected,
                    onSelect = {
                        selected = text
                    }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.size10))
            }
        }

        Spacer(modifier = Modifier.weight(WEIGHT_1))

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            title = btnTitle,
            onClick = {
                onOptionSelected(selectedInterest)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInterestSelector() {
    DatingAppTheme {
        InterestSelector(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewInterestSelector() {
    DatingAppTheme(darkTheme = true) {
        InterestSelector(
            selectedOption = stringResource(id = R.string.scr_auth_interest_select_relationship)
        )
    }
}