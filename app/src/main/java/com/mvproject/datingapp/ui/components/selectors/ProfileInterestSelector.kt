/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 18:35
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.selectors

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.ProfileInterest
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.backColor
import com.mvproject.datingapp.utils.borderColor
import com.mvproject.datingapp.utils.textColor

@Composable
fun ProfileInterestSelector(
    modifier: Modifier = Modifier,
    selectedOption: String = stringResource(ProfileInterest.INTEREST_LOVE.title),
    radioOptions: List<ProfileInterest> = ProfileInterest.values().toList(),
    title: String = stringResource(id = R.string.scr_auth_interest_select_title),
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

        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onOptionSelected(selectedInterest)
            }
        )
    }
}

@Composable
fun RadioSelector(
    text: String,
    logo: Painter? = null,
    isSelected: Boolean,
    onSelect: (String) -> Unit = {}
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = {
                    onSelect(text)
                }
            )
            .background(
                color = backColor(isSelected),
                shape = MaterialTheme.shapes.large
            )
            .border(
                MaterialTheme.dimens.size1,
                color = borderColor(isSelected),
                shape = MaterialTheme.shapes.large
            )
            .padding(horizontal = MaterialTheme.dimens.size8),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        logo?.let { img ->
            Icon(
                modifier = Modifier
                    .padding(start = MaterialTheme.dimens.size8),
                painter = img,
                contentDescription = text,
                tint = textColor(isSelected)
            )
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = MaterialTheme.dimens.size12),
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor(isSelected),
        )

        RadioButton(
            selected = isSelected,
            onClick = {
                onSelect(text)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )
    }
}

@Composable
fun CheckSelector(
    text: String,
    logo: Painter? = null,
    isSelected: Boolean,
    onSelect: (String) -> Unit = {}
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = {
                    onSelect(text)
                }
            )
            .background(
                color = backColor(isSelected),
                shape = MaterialTheme.shapes.large
            )
            .border(
                MaterialTheme.dimens.size1,
                color = borderColor(isSelected),
                shape = MaterialTheme.shapes.large
            )
            .padding(horizontal = MaterialTheme.dimens.size8),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        logo?.let { img ->
            Icon(
                modifier = Modifier
                    .padding(start = MaterialTheme.dimens.size8),
                painter = img,
                contentDescription = text,
                tint = textColor(isSelected)
            )
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = MaterialTheme.dimens.size12),
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor(isSelected),
        )

        Checkbox(
            checked = isSelected,
            onCheckedChange = {
                onSelect(text)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondaryContainer,
                checkmarkColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeProfileInterestSelector() {
    DatingAppTheme {
        ProfileInterestSelector(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfileInterestSelector() {
    DatingAppTheme(darkTheme = true) {
        ProfileInterestSelector(
            selectedOption = stringResource(id = R.string.scr_auth_interest_select_relationship)
        )
    }
}