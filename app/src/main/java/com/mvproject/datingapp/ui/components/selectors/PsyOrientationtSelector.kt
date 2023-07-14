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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfilePsyOrientation
import com.mvproject.datingapp.ui.components.RadioSelector
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_5

@Composable
fun PsyOrientationSelector(
    modifier: Modifier = Modifier,
    selectedOption: String = stringResource(ProfilePsyOrientation.PSY_ORIENTATION_BETWEEN.title),
    radioOptions: List<ProfilePsyOrientation> = ProfilePsyOrientation.values().toList(),
    onOptionSelected: (ProfilePsyOrientation) -> Unit = {}
) {
    val context = LocalContext.current
    var selected by remember {
        mutableStateOf(selectedOption)
    }
    val selectedPsyOrientation by remember {
        derivedStateOf {
            ProfilePsyOrientation.values().first {
                context.getText(it.title) == selected
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            modifier = Modifier.weight(WEIGHT_5),
            verticalArrangement = Arrangement
                .spacedBy(MaterialTheme.dimens.size10)
        ) {
            items(radioOptions) { item ->
                val text = stringResource(id = item.title)
                val isSelected = text == selected

                RadioSelector(
                    text = text,
                    isSelected = isSelected,
                    onSelect = {
                        selected = text
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.btn_title_next),
            onClick = {
                onOptionSelected(selectedPsyOrientation)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeProfilePsyOrientationSelector() {
    DatingAppTheme {
        PsyOrientationSelector(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfilePsyOrientationSelector() {
    DatingAppTheme(darkTheme = true) {
        PsyOrientationSelector(
            selectedOption = stringResource(ProfilePsyOrientation.PSY_ORIENTATION_EXTRAVERT.title)
        )
    }
}