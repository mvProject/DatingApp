/*
 * Create by Medvediev Viktor
 * last update: 09.06.23, 10:40
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.screens.authorization.state.ProfileGoal
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun ProfileGoalSelector(
    modifier: Modifier = Modifier,
    selectedOption: String = "",
    radioOptions: List<ProfileGoal> = ProfileGoal.values().toList(),
    title: String = stringResource(id = R.string.scr_auth_goal_select_title),
    onOptionSelected: (String) -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = modifier.padding(
                horizontal = MaterialTheme.dimens.size24
            ),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        Column {
            radioOptions.forEach { option ->
                val text = stringResource(id = option.title)

                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        .border(
                            MaterialTheme.dimens.size1,
                            color = MaterialTheme.colorScheme.outline,
                            shape = MaterialTheme.shapes.large
                        )
                        .padding(horizontal = MaterialTheme.dimens.size8),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = Modifier
                            .padding(start = MaterialTheme.dimens.size8),
                        painter = painterResource(id = option.logo),
                        contentDescription = text
                    )

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = MaterialTheme.dimens.size12),
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )

                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.secondaryContainer,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.size10))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeProfileGoalSelector() {
    DatingAppTheme {
        ProfileGoalSelector(

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfileGoalSelector() {
    DatingAppTheme(darkTheme = true) {
        ProfileGoalSelector(

        )
    }
}