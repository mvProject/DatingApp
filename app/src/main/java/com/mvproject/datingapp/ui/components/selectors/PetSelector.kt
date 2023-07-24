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
import com.mvproject.datingapp.data.enums.ProfilePets
import com.mvproject.datingapp.ui.components.CheckSelector
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_5

@Composable
fun PetSelector(
    modifier: Modifier = Modifier,
    selectedOptions: List<ProfilePets> = emptyList(),
    radioOptions: List<ProfilePets> = ProfilePets.values().toList(),
    onOptionSelected: (List<ProfilePets>) -> Unit = {}
) {
    val context = LocalContext.current

    val selectedPets = remember {
        mutableStateListOf<ProfilePets>().also {
            it.addAll(selectedOptions)
        }
    }

    val selected by remember {
        derivedStateOf {
            selectedPets.map { context.getText(it.title) }
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
                val isSelected = text in selected

                CheckSelector(
                    text = text,
                    isSelected = isSelected,
                    onSelect = {
                        if (ProfilePets.PETS_NO in selectedPets) {
                            selectedPets.remove(ProfilePets.PETS_NO)
                        }
                        if (item == ProfilePets.PETS_NO) {
                            selectedPets.clear()
                        }
                        selectedPets.add(item)
                    },
                    onDeselect = {
                        selectedPets.remove(item)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.btn_title_next),
            onClick = {
                onOptionSelected(selectedPets.toList())
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeProfilePetSelector() {
    DatingAppTheme {
        PetSelector()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfilePetSelector() {
    DatingAppTheme(darkTheme = true) {
        PetSelector()
    }
}