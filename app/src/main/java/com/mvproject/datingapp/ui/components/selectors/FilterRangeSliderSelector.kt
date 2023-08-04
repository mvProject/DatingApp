/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 18:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.selectors

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.BorderButton
import com.mvproject.datingapp.ui.components.buttons.ColorButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.DEFAULT_AGE_RANGE
import com.mvproject.datingapp.utils.INT_ZERO
import com.mvproject.datingapp.utils.WEIGHT_1

@Composable
fun FilterRangeSliderSelector(
    modifier: Modifier = Modifier,
    initialStart: Int = INT_ZERO,
    initialEnd: Int = INT_ZERO,
    isCancelEnabled: Boolean = false,
    @StringRes titleRange: Int = R.string.profile_filter_age_value,
    valueRange: ClosedFloatingPointRange<Float> = DEFAULT_AGE_RANGE,
    btnTitle: String = stringResource(id = R.string.btn_title_save),
    onOptionSelected: (Int, Int) -> Unit = { _: Int, _: Int -> },
    onCancel: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var sliderValue by remember {
            mutableStateOf(initialStart.toFloat()..initialEnd.toFloat())
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            text = stringResource(
                id = titleRange,
                sliderValue.start.toInt(),
                sliderValue.endInclusive.toInt()
            ),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        RangeSlider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderValue,
            onValueChange = { value ->
                sliderValue = value
            },
            steps = 0,
            valueRange = valueRange,
            onValueChangeFinished = {

            },
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.outline
            )
        )

        Spacer(modifier = Modifier.weight(WEIGHT_1))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size16)
        ) {
            if (isCancelEnabled) {
                BorderButton(
                    modifier = Modifier.weight(WEIGHT_1),
                    onClick = onCancel
                )
            }

            ColorButton(
                modifier = Modifier.weight(WEIGHT_1),
                title = btnTitle,
                onClick = {
                    onOptionSelected(
                        sliderValue.start.toInt(),
                        sliderValue.endInclusive.toInt()
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilterRangeSliderSelector() {
    DatingAppTheme {
        FilterRangeSliderSelector()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewFilterRangeSliderSelector() {
    DatingAppTheme(darkTheme = true) {
        FilterRangeSliderSelector(isCancelEnabled = true)
    }
}