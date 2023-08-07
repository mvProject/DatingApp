/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 17:45
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.mvproject.datingapp.data.model.UserLocation
import com.mvproject.datingapp.dummy.DEFAULT_LOCATION_INDEX
import com.mvproject.datingapp.dummy.cities
import com.mvproject.datingapp.dummy.countries
import com.mvproject.datingapp.dummy.regions
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.dialog.BottomDialog
import com.mvproject.datingapp.ui.components.menuoptions.OptionSelector
import com.mvproject.datingapp.ui.components.wheelselector.WheelSelector
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun LocationSelector(
    modifier: Modifier = Modifier,
    userLocation: UserLocation = UserLocation(),
    title: String = stringResource(id = R.string.scr_auth_location_select_title),
    btnTitle: String = stringResource(id = R.string.btn_title_continue),
    onLocationSelected: (UserLocation) -> Unit = {}
) {

    var currentLocation by remember {
        mutableStateOf(userLocation)
    }

    val isCountrySelectEnabled = remember { mutableStateOf(false) }
    val isRegionSelectEnabled = remember { mutableStateOf(false) }
    val isCitySelectEnabled = remember { mutableStateOf(false) }

    val isCanOpenSelector by remember {
        derivedStateOf {
            !isCountrySelectEnabled.value && !isRegionSelectEnabled.value && !isCitySelectEnabled.value
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = MaterialTheme.dimens.size12)
                .padding(start = MaterialTheme.dimens.size24),
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

            OptionSelector(
                title = stringResource(id = R.string.scr_auth_location_country_title),
                selected = currentLocation.country,
                onChange = {
                    if (isCanOpenSelector) {
                        isCountrySelectEnabled.value = true
                    }
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

            OptionSelector(
                title = stringResource(id = R.string.scr_auth_location_region_title),
                selected = currentLocation.region,
                onChange = {
                    if (isCanOpenSelector) {
                        isRegionSelectEnabled.value = true
                    }
                }
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

            OptionSelector(
                title = stringResource(id = R.string.scr_auth_location_city_title),
                selected = currentLocation.city,
                onChange = {
                    if (isCanOpenSelector) {
                        isCitySelectEnabled.value = true
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            GradientButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.dimens.size24),
                title = btnTitle,
                onClick = {
                    onLocationSelected(currentLocation)
                }
            )
        }

        BottomDialog(
            isVisible = isCountrySelectEnabled
        ) {
            WheelSelector(
                items = countries,
                initial = DEFAULT_LOCATION_INDEX,
                onSelect = {
                    val country = countries[it]
                    isCountrySelectEnabled.value = false
                    val location = currentLocation.copy(country = country)
                    currentLocation = location
                }
            )
        }

        BottomDialog(
            isVisible = isRegionSelectEnabled
        ) {
            WheelSelector(
                items = regions,
                initial = DEFAULT_LOCATION_INDEX,
                onSelect = {
                    val region = regions[it]
                    isRegionSelectEnabled.value = false
                    val location = currentLocation.copy(region = region)
                    currentLocation = location
                }
            )
        }

        BottomDialog(
            isVisible = isCitySelectEnabled
        ) {
            WheelSelector(
                items = cities,
                initial = DEFAULT_LOCATION_INDEX,
                onSelect = {
                    val city = cities[it]
                    isCitySelectEnabled.value = false
                    val location = currentLocation.copy(city = city)
                    currentLocation = location
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationSelector() {
    DatingAppTheme {
        LocationSelector()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewLocationSelector() {
    DatingAppTheme(darkTheme = true) {
        LocationSelector()
    }
}