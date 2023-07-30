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
import com.mvproject.datingapp.data.enums.profile.ProfileZodiac
import com.mvproject.datingapp.ui.components.RadioSplitSelector
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.WEIGHT_5

@Composable
fun ZodiacSelector(
    modifier: Modifier = Modifier,
    selectedOption: String = stringResource(ProfileZodiac.ZODIAC_LEO.title),
    radioOptions: List<ProfileZodiac> = ProfileZodiac.values().toList(),
    btnTitle: String = stringResource(id = R.string.btn_title_next),
    onOptionSelected: (ProfileZodiac) -> Unit = {}
) {
    val context = LocalContext.current
    var selected by remember {
        mutableStateOf(selectedOption)
    }
    val selectedZodiac by remember {
        derivedStateOf {
            ProfileZodiac.values().first {
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

                val textDate = if (item.date != INT_ZERO) {
                    stringResource(id = item.date)
                } else STRING_EMPTY

                RadioSplitSelector(
                    textBefore = text,
                    textAfter = textDate,
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
            title = btnTitle,
            onClick = {
                onOptionSelected(selectedZodiac)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewZodiacSelector() {
    DatingAppTheme {
        ZodiacSelector(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewZodiacSelector() {
    DatingAppTheme(darkTheme = true) {
        ZodiacSelector(
            selectedOption = stringResource(ProfileZodiac.ZODIAC_AQUARIUS.title)
        )
    }
}