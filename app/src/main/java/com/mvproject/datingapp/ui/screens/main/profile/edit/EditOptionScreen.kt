/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:54
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.profile.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.input.about.AboutInput
import com.mvproject.datingapp.ui.components.input.work.WorkInput
import com.mvproject.datingapp.ui.components.selectors.AlcoholSelector
import com.mvproject.datingapp.ui.components.selectors.ChildrenSelector
import com.mvproject.datingapp.ui.components.selectors.HeightSelector
import com.mvproject.datingapp.ui.components.selectors.InterestSelector
import com.mvproject.datingapp.ui.components.selectors.LanguagesSelector
import com.mvproject.datingapp.ui.components.selectors.LocationSelector
import com.mvproject.datingapp.ui.components.selectors.MaritalSelector
import com.mvproject.datingapp.ui.components.selectors.OrientationSelector
import com.mvproject.datingapp.ui.components.selectors.PetSelector
import com.mvproject.datingapp.ui.components.selectors.PsyOrientationSelector
import com.mvproject.datingapp.ui.components.selectors.ReligionSelector
import com.mvproject.datingapp.ui.components.selectors.SmokeSelector
import com.mvproject.datingapp.ui.components.selectors.ZodiacSelector
import com.mvproject.datingapp.ui.screens.main.profile.edit.action.EditOptionAction
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditOptionDataState
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditProfileOption
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditProfileOption.Companion.stateLogo
import com.mvproject.datingapp.ui.screens.main.profile.edit.state.EditProfileOption.Companion.stateTitle
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun EditOptionScreen(
    viewModel: EditOptionViewModel,
    onNavigationBack: () -> Unit = {},
) {
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = profileState) {
        if (profileState.isComplete) {
            onNavigationBack()
        }
    }

    EditOptionView(
        state = profileState,
        onAction = viewModel::processAction,
        onBackClick = onNavigationBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOptionView(
    state: EditOptionDataState = EditOptionDataState(),
    onAction: (EditOptionAction) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(MaterialTheme.dimens.size16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state.currentOption) {
                EditProfileOption.INTEREST -> {
                    InterestSelector(
                        selectedOption = stringResource(id = state.profileInterest.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { interest ->
                            onAction(EditOptionAction.UpdateInterest(interest))
                        }
                    )
                }

                EditProfileOption.LOCATION -> {
                    LocationSelector(
                        userLocation = state.profileLocation,
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onLocationSelected = { location ->
                            onAction(EditOptionAction.UpdateLocation(location))
                        }
                    )
                }

                EditProfileOption.ABOUT -> {
                    AboutInput(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        initial = state.profileAbout,
                        title = stringResource(state.currentOption.stateTitle()),
                        logo = state.currentOption.stateLogo(),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                    ) { text ->
                        onAction(EditOptionAction.UpdateProfileAbout(text))
                    }
                }

                EditProfileOption.WORK -> {
                    WorkInput(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        initial = state.profileWork,
                        title = stringResource(state.currentOption.stateTitle()),
                        logo = state.currentOption.stateLogo(),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                    ) { workInfo ->
                        onAction(EditOptionAction.UpdateProfileWork(workInfo))
                    }
                }

                else -> {
                    Image(
                        modifier = Modifier
                            .size(MaterialTheme.dimens.size200),
                        painter = painterResource(id = state.currentOption.stateLogo()),
                        contentDescription = state.currentOption.name
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.dimens.size8),
                        text = stringResource(id = state.currentOption.stateTitle()),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                        fontSize = MaterialTheme.dimens.font22
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

            when (state.currentOption) {
                EditProfileOption.ORIENTATION -> {
                    OrientationSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOption = stringResource(id = state.profileOrientation.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { orientation ->
                            onAction(EditOptionAction.UpdateProfileOrientation(orientation))
                        }
                    )
                }

                EditProfileOption.MARITAL_STATUS -> {
                    MaritalSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOption = stringResource(id = state.profileMarital.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { status ->
                            onAction(EditOptionAction.UpdateProfileMarital(status))
                        }
                    )
                }

                EditProfileOption.CHILDREN -> {
                    ChildrenSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOption = stringResource(id = state.profileChildren.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { status ->
                            onAction(EditOptionAction.UpdateProfileChildren(status))
                        }
                    )
                }

                EditProfileOption.HEIGHT -> {
                    HeightSelector(
                        initial = state.profileHeight,
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { userHeight ->
                            onAction(EditOptionAction.UpdateProfileHeight(userHeight))
                        }
                    )
                }

                EditProfileOption.ZODIAC -> {
                    ZodiacSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOption = stringResource(id = state.profileZodiac.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { sign ->
                            onAction(EditOptionAction.UpdateProfileZodiac(sign))
                        }
                    )
                }

                EditProfileOption.ALCOHOL -> {
                    AlcoholSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOption = stringResource(id = state.profileAlcohol.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { status ->
                            onAction(EditOptionAction.UpdateProfileAlcohol(status))
                        }
                    )
                }

                EditProfileOption.SMOKE -> {
                    SmokeSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOption = stringResource(id = state.profileSmoke.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { status ->
                            onAction(EditOptionAction.UpdateProfileSmoke(status))
                        }
                    )
                }

                EditProfileOption.PSY_ORIENTATION -> {
                    PsyOrientationSelector(
                        selectedOption = stringResource(id = state.profilePsyOrientation.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { orientation ->
                            onAction(
                                EditOptionAction.UpdateProfilePsyOrientation(
                                    orientation
                                )
                            )
                        }
                    )
                }

                EditProfileOption.RELIGION -> {
                    ReligionSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOption = stringResource(id = state.profileReligion.title),
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { religion ->
                            onAction(EditOptionAction.UpdateProfileReligion(religion))
                        }
                    )
                }

                EditProfileOption.LANGUAGES -> {
                    LanguagesSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOptions = state.profileLanguages,
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { languages ->
                            onAction(EditOptionAction.UpdateProfileLanguages(languages))
                        }
                    )
                }

                EditProfileOption.PETS -> {
                    PetSelector(
                        modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                        selectedOptions = state.profilePets,
                        btnTitle = stringResource(id = R.string.btn_title_ok),
                        onOptionSelected = { pets ->
                            onAction(EditOptionAction.UpdateProfilePets(pets))
                        }
                    )
                }

                else -> {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditOptionView() {
    DatingAppTheme {
        EditOptionView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewEditOptionView() {
    DatingAppTheme(darkTheme = true) {
        EditOptionView()
    }
}