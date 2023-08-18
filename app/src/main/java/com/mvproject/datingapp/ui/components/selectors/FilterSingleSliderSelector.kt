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
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.ColorButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.INT_ZERO

@Composable
fun FilterSingleSliderSelector(
    modifier: Modifier = Modifier,
    initial: Int = INT_ZERO,
    multiplier: Int = 10,
    step: Int = 9,
    btnTitle: String = stringResource(id = R.string.btn_title_save),
    onOptionSelected: (Int) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var sliderValue by remember {
            mutableStateOf((initial / multiplier).toFloat() / 10)
        }

        val displayValue by remember {
            derivedStateOf {
                (sliderValue * 10).toInt() * multiplier
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            text = displayValue.toString(),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderValue,
            onValueChange = { value ->
                sliderValue = value
            },
            steps = step,
            valueRange = 0.1f..1f,
            onValueChangeFinished = {

            },
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.outline,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
            )
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        ColorButton(
            modifier = Modifier
                .fillMaxWidth(),
            title = btnTitle,
            onClick = {
                onOptionSelected(displayValue)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilterSingleSliderSelector() {
    DatingAppTheme {
        FilterSingleSliderSelector()
    }
}