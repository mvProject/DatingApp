/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 16:02
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.wheelselector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.dummy.countries
import com.mvproject.datingapp.ui.components.buttons.DarkButton
import com.mvproject.datingapp.ui.components.wheelselector.wheelpicker.FVerticalWheelPicker
import com.mvproject.datingapp.ui.components.wheelselector.wheelpicker.rememberFWheelPickerState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.INT_ZERO

@Composable
fun WheelSelector(
    items: List<String> = emptyList(),
    initial: Int = INT_ZERO,
    onSelect: (Int) -> Unit = {}
) {
    require(items.isNotEmpty())

    var selected by remember {
        mutableStateOf(INT_ZERO)
    }

    Column(
        modifier = Modifier
            .padding(vertical = MaterialTheme.dimens.size16),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = rememberFWheelPickerState(
            initialIndex = initial
        )
        LaunchedEffect(state) {
            snapshotFlow { state.currentIndex }
                .collect {
                    selected = it
                }
        }

        FVerticalWheelPicker(
            modifier = Modifier.width(MaterialTheme.dimens.size96),
            state = state,
            unfocusedCount = 2,
            count = items.count(),
        ) { index ->
            val color = if (state.currentIndex == index)
                MaterialTheme.colorScheme.secondary
            else
                MaterialTheme.colorScheme.onSurface

            val style = if (state.currentIndex == index)
                MaterialTheme.typography.bodyLarge
            else
                MaterialTheme.typography.labelLarge
            Text(
                text = items[index],
                color = color,
                style = style
            )
        }

        DarkButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size16),
            title = stringResource(id = R.string.btn_title_save),
            onClick = {
                onSelect(selected)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeWheelSelector() {
    DatingAppTheme {
        WheelSelector(countries)
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewWheelSelector() {
    DatingAppTheme(darkTheme = true) {
        WheelSelector(countries)
    }
}