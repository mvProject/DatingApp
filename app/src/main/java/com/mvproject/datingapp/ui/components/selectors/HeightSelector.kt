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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.model.UserHeight
import com.mvproject.datingapp.ui.components.CheckSelectorInverted
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun HeightSelector(
    modifier: Modifier = Modifier,
    initial: UserHeight = UserHeight(),
    onOptionSelected: (UserHeight) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var sliderValue by remember {
            mutableStateOf(initial.height.toFloat())
        }
        var isHeightNotVisible by remember {
            mutableStateOf(initial.isHeightNotVisible)
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            text = sliderValue.toInt().toString(),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall
        )

        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderValue,
            onValueChange = { value ->
                sliderValue = value
            },
            steps = 0,
            valueRange = 150f..200f,
            onValueChangeFinished = {
                isHeightNotVisible = false
            },
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.outline
            )
        )

        CheckSelectorInverted(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.option_not_set),
            isSelected = isHeightNotVisible,
            onSelect = {
                isHeightNotVisible = !isHeightNotVisible
            }
        )

        Spacer(modifier = Modifier.weight(WEIGHT_1))

        GradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            title = stringResource(id = R.string.btn_title_next),
            onClick = {
                onOptionSelected(
                    UserHeight(
                        height = sliderValue.toInt(),
                        isHeightNotVisible = isHeightNotVisible
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeProfileHeightSelector() {
    DatingAppTheme {
        HeightSelector(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfileHeightSelector() {
    DatingAppTheme(darkTheme = true) {
        HeightSelector(

        )
    }
}