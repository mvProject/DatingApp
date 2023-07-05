/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 19:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.input.otpDate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.dialog.InfoDialog
import com.mvproject.datingapp.ui.components.message.PrivacyField
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.calculatAgeMillis
import com.mvproject.datingapp.utils.isValidDateMillis
import com.mvproject.datingapp.utils.toDateMillis
import com.mvproject.datingapp.utils.toDatePattern
import timber.log.Timber

@Composable
fun DateSelector(
    modifier: Modifier = Modifier,
    initial: String = STRING_EMPTY,
    title: String = stringResource(id = R.string.scr_auth_date_select_title, "Test"),
    description: String = stringResource(id = R.string.scr_auth_date_select_description),
    privacyText: String = STRING_EMPTY,
    onDateSelected: (Long) -> Unit = {}
) {

    var dateValue by remember {
        mutableStateOf(initial)
    }

    val isDateNotValidDialogOpen = remember { mutableStateOf(false) }
    val isAgeNotValidDialogOpen = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxSize()
            .padding(
                vertical = MaterialTheme.dimens.size12,
                horizontal = MaterialTheme.dimens.size24
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size8),
            text = title,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

        DateTextField(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.dimens.size16),
            dateText = dateValue,
            onDateTextChange = { value, dateFilled ->
                dateValue = value
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

        Text(
            text = description,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        if (privacyText.isNotEmpty()) {
            PrivacyField(
                text = privacyText
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))
        }

        GradientButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    val dateMillis = toDateMillis(dateValue.toDatePattern())
                    val isValidDateFormat =
                        dateMillis != null && isValidDateMillis(dateMillis)
                    if (isValidDateFormat) {
                        val isValidAge = calculatAgeMillis(dateMillis!!) >= 18
                        if (isValidAge) {
                            onDateSelected(dateMillis)
                        } else {
                            isAgeNotValidDialogOpen.value = true
                        }
                    } else {
                        isDateNotValidDialogOpen.value = true
                    }
                } catch (e: IllegalArgumentException) {
                    Timber.e("testing Error: ${e.message}")
                    isDateNotValidDialogOpen.value = true
                }
            }
        )
    }

    InfoDialog(
        isDialogOpen = isDateNotValidDialogOpen,
        title = stringResource(id = R.string.dlg_date_validation_error_title),
        description = stringResource(id = R.string.dlg_date_validation_error_description),
        onConfirm = {
            isDateNotValidDialogOpen.value = false
        }
    )

    InfoDialog(
        isDialogOpen = isAgeNotValidDialogOpen,
        title = stringResource(id = R.string.dlg_date_validation_error_title),
        description = stringResource(id = R.string.dlg_age_validation_error_description),
        onConfirm = {
            isAgeNotValidDialogOpen.value = false
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCodeDateSelector() {
    DatingAppTheme {
        DateSelector(
            initial = "12"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewDateSelector() {
    DatingAppTheme(darkTheme = true) {
        DateSelector(
            initial = "12041985"
        )
    }
}