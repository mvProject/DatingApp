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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileCharacteristic
import com.mvproject.datingapp.ui.components.buttons.ColorButton
import com.mvproject.datingapp.ui.components.composable.view.CheckSelector
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_5

@Composable
fun FilterMultiSelector(
    modifier: Modifier = Modifier,
    selectedOptions: List<ProfileCharacteristic> = emptyList(),
    options: List<ProfileCharacteristic> = emptyList(),
    btnTitle: String = stringResource(id = R.string.btn_title_save),
    onOptionSelected: (List<ProfileCharacteristic>) -> Unit = {}
) {

    val context = LocalContext.current

    val selectedValues = remember {
        mutableStateListOf<ProfileCharacteristic>().also {
            it.addAll(selectedOptions)
        }
    }

    val selected by remember {
        derivedStateOf {
            selectedValues.map { context.getText(it.title) }
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
            items(options) { item ->
                val text = stringResource(id = item.title)
                val isSelected = text in selected

                CheckSelector(
                    text = text,
                    isSelected = isSelected,
                    onSelect = {
                        selectedValues.add(item)
                    },
                    onDeselect = {
                        selectedValues.remove(item)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        ColorButton(
            modifier = Modifier
                .fillMaxWidth(),
            title = btnTitle,
            onClick = {
                onOptionSelected(selectedValues.toList())
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilterMultiSelector() {
    DatingAppTheme {
        FilterMultiSelector()
    }
}