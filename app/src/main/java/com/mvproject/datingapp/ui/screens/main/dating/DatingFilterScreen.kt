/*
 * Create by Medvediev Viktor
 * last update: 18.07.23, 18:54
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.dating

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mvproject.datingapp.R
import com.mvproject.datingapp.data.enums.filter.FilterAlcohol
import com.mvproject.datingapp.data.enums.filter.FilterCharacter
import com.mvproject.datingapp.data.enums.filter.FilterChildren
import com.mvproject.datingapp.data.enums.filter.FilterGender
import com.mvproject.datingapp.data.enums.filter.FilterInterest
import com.mvproject.datingapp.data.enums.filter.FilterLanguage
import com.mvproject.datingapp.data.enums.filter.FilterMarital
import com.mvproject.datingapp.data.enums.filter.FilterOrientation
import com.mvproject.datingapp.data.enums.filter.FilterPets
import com.mvproject.datingapp.data.enums.filter.FilterReligion
import com.mvproject.datingapp.data.enums.filter.FilterSmoke
import com.mvproject.datingapp.data.enums.filter.FilterZodiac
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.dialog.BottomDialog
import com.mvproject.datingapp.ui.components.menuoptions.OptionFilter
import com.mvproject.datingapp.ui.components.selectors.FilterMultiSelector
import com.mvproject.datingapp.ui.components.selectors.FilterRangeSliderSelector
import com.mvproject.datingapp.ui.components.selectors.FilterSingleSelector
import com.mvproject.datingapp.ui.components.selectors.FilterSingleSliderSelector
import com.mvproject.datingapp.ui.screens.main.dating.action.FilterChangeAction
import com.mvproject.datingapp.ui.screens.main.dating.action.FilterCleanAction
import com.mvproject.datingapp.ui.screens.main.dating.state.DatingFilterState
import com.mvproject.datingapp.ui.screens.main.dating.state.FilterProfileOption
import com.mvproject.datingapp.ui.screens.main.dating.state.FilterProfileOption.Companion.stateTitle
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.DEFAULT_HEIGHT_RANGE
import com.mvproject.datingapp.utils.DISTANCE_STEP_MULTIPLIER
import com.mvproject.datingapp.utils.SCALE_25
import com.mvproject.datingapp.utils.SCALE_30
import com.mvproject.datingapp.utils.SCALE_40
import com.mvproject.datingapp.utils.SCALE_50
import com.mvproject.datingapp.utils.SCALE_80
import com.mvproject.datingapp.utils.STRING_COMA_SPACE
import com.mvproject.datingapp.utils.STRING_EMPTY

@Composable
fun DatingFilterScreen(
    viewModel: DatingFilterViewModel,
    onNavigationBack: () -> Unit = {}
) {
    val filterState by viewModel.datingFilterState.collectAsStateWithLifecycle()

    DatingFilterView(
        state = filterState,
        onChangeAction = viewModel::processChangeAction,
        onCleanAction = viewModel::processCleanAction,
        onApplyFilterClick = viewModel::applyFilters,
        onBackClick = onNavigationBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatingFilterView(
    state: DatingFilterState = DatingFilterState(),
    onChangeAction: (FilterChangeAction) -> Unit = {},
    onCleanAction: (FilterCleanAction) -> Unit = {},
    onApplyFilterClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var filterType by remember { mutableStateOf(FilterProfileOption.NONE) }
    val isFilterEditOpen = remember { mutableStateOf(false) }

    BackHandler(true) {
        if (isFilterEditOpen.value) {
            filterType = FilterProfileOption.NONE
            isFilterEditOpen.value = false
        } else {
            onBackClick()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.screen_title_profile_filter),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                    )
                },
                actions = {
                    Text(
                        modifier = Modifier
                            .clickable {
                                onCleanAction(FilterCleanAction.CleanAll)
                            }
                            .padding(end = MaterialTheme.dimens.size12),
                        text = stringResource(id = R.string.btn_title_clean),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_navigate_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = MaterialTheme.dimens.size16)
            ) {
                OptionFilter(
                    title = stringResource(id = R.string.profile_filter_option_gender_interest),
                    selected = stringResource(id = state.filterData.filterGender.title),
                    isRequired = true,
                    onChange = {
                        filterType = FilterProfileOption.GENDER_INTEREST
                        isFilterEditOpen.value = true
                    }
                )

                OptionFilter(
                    title = stringResource(id = R.string.profile_filter_option_age),
                    selected = stringResource(
                        id = R.string.profile_filter_age_value,
                        state.filterData.startAge,
                        state.filterData.endAge
                    ),
                    isRequired = true,
                    onChange = {
                        filterType = FilterProfileOption.AGE
                        isFilterEditOpen.value = true
                    }
                )
                OptionFilter(
                    title = stringResource(id = R.string.profile_filter_option_distance),
                    selected = "${state.filterData.distance}",
                    isRequired = true,
                    onChange = {
                        filterType = FilterProfileOption.DISTANCE
                        isFilterEditOpen.value = true
                    }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.size20))

                Text(
                    modifier = Modifier.padding(start = MaterialTheme.dimens.size16),
                    text = stringResource(id = R.string.profile_filter_more),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineSmall,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))

                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_orientation),
                    title = stringResource(id = R.string.profile_edit_option_sexual_orientation),
                    selected = if (state.filterData.filterOrientation.isValueSet)
                        stringResource(id = state.filterData.filterOrientation.title)
                    else STRING_EMPTY,
                    onChange = {
                        filterType = FilterProfileOption.ORIENTATION
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanOrientation)
                    }
                )
                val maritals = state.filterData.filterMaritals.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_marital),
                    title = stringResource(id = R.string.profile_edit_option_marital_status),
                    selected = maritals.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.MARITAL_STATUS
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanMarital)
                    }
                )
                val interests = state.filterData.filterInterests.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_interest),
                    title = stringResource(id = R.string.profile_filter_option_interest),
                    selected = interests.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.INTEREST
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanInterest)
                    }
                )
                val childrens = state.filterData.filterChildrens.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_children),
                    title = stringResource(id = R.string.profile_edit_option_children),
                    selected = childrens.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.CHILDREN
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanChildren)
                    }
                )

                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_height),
                    title = stringResource(id = R.string.profile_edit_option_height),
                    selected = if (state.filterData.isHeightSet) stringResource(
                        id = R.string.profile_filter_height_value,
                        state.filterData.startHeight,
                        state.filterData.endHeight
                    ) else STRING_EMPTY,
                    onChange = {
                        filterType = FilterProfileOption.HEIGHT
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanHeight)
                    }
                )
                val zodiacs = state.filterData.filterZodiacs.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_zodiac),
                    title = stringResource(id = R.string.profile_edit_option_zodiac_sign),
                    selected = zodiacs.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.ZODIAC
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanZodiac)
                    }
                )
                val alcohols = state.filterData.filterAlcohols.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_alcohol),
                    title = stringResource(id = R.string.profile_edit_option_alcohol),
                    selected = alcohols.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.ALCOHOL
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanAlcohol)
                    }
                )
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_smoking),
                    title = stringResource(id = R.string.profile_edit_option_smoking),
                    selected = if (state.filterData.filterSmoke.isValueSet)
                        stringResource(id = state.filterData.filterSmoke.title)
                    else STRING_EMPTY,
                    onChange = {
                        filterType = FilterProfileOption.SMOKE
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanSmoke)
                    }
                )
                val psyOrientations = state.filterData.filterCharacter.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_character),
                    title = stringResource(id = R.string.profile_edit_option_character),
                    selected = psyOrientations.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.CHARACTER
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanCharacter)
                    }
                )
                val religions = state.filterData.filterReligions.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_religion),
                    title = stringResource(id = R.string.profile_edit_option_religion),
                    selected = religions.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.RELIGION
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanReligion)
                    }
                )

                val langs = state.filterData.filterLanguages.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_languages),
                    title = stringResource(id = R.string.profile_edit_option_languages),
                    selected = langs.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.LANGUAGES
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanLanguages)
                    }
                )

                val pets = state.filterData.filterPets.map {
                    stringResource(id = it.title)
                }
                OptionFilter(
                    logo = painterResource(id = R.drawable.ic_edit_pets),
                    title = stringResource(id = R.string.profile_edit_option_pets),
                    selected = pets.joinToString(STRING_COMA_SPACE),
                    onChange = {
                        filterType = FilterProfileOption.PETS
                        isFilterEditOpen.value = true
                    },
                    onClear = {
                        onCleanAction(FilterCleanAction.CleanPets)
                    }
                )
            }

            GradientButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.size16)
                    .align(Alignment.BottomCenter),
                title = stringResource(id = R.string.btn_title_filter_apply),
                onClick = {
                    onApplyFilterClick()
                    onBackClick()
                }
            )
        }


        BottomDialog(
            isVisible = isFilterEditOpen
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(MaterialTheme.dimens.size24),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = filterType.stateTitle()),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.displaySmall,
                    fontSize = MaterialTheme.dimens.font22
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

                when (filterType) {
                    FilterProfileOption.GENDER_INTEREST -> {
                        FilterSingleSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_40),
                            selectedOption = stringResource(id = state.filterData.filterGender.title),
                            options = FilterGender.entries,
                            onOptionSelected = { value ->
                                val result = value as FilterGender
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.GenderFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.AGE -> {
                        FilterRangeSliderSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_25),
                            initialStart = state.filterData.startAge,
                            initialEnd = state.filterData.endAge,
                            onOptionSelected = { start, end ->
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(
                                    FilterChangeAction.AgeFilter(
                                        start = start,
                                        end = end
                                    )
                                )
                            }
                        )
                    }

                    FilterProfileOption.DISTANCE -> {
                        FilterSingleSliderSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_30),
                            initial = state.filterData.distance,
                            multiplier = DISTANCE_STEP_MULTIPLIER,
                            onOptionSelected = { value ->
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.DistanceFilter(value))
                            }
                        )
                    }

                    FilterProfileOption.ORIENTATION -> {
                        FilterSingleSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_50),
                            selectedOption = stringResource(id = state.filterData.filterOrientation.title),
                            options = FilterOrientation.entries.filter { it != FilterOrientation.NOT_SET },
                            onOptionSelected = { value ->
                                val result = value as FilterOrientation
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.OrientationFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.MARITAL_STATUS -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_40),
                            selectedOptions = state.filterData.filterMaritals,
                            options = FilterMarital.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterMarital
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.MaritalFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.INTEREST -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_50),
                            selectedOptions = state.filterData.filterInterests,
                            options = FilterInterest.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterInterest
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.InterestFilter(result))
                            }
                        )

                    }

                    FilterProfileOption.CHILDREN -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_50),
                            selectedOptions = state.filterData.filterChildrens,
                            options = FilterChildren.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterChildren
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.ChildrenFilter(result))
                            }
                        )

                    }

                    FilterProfileOption.HEIGHT -> {
                        FilterRangeSliderSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_25),
                            initialStart = state.filterData.startHeight,
                            initialEnd = state.filterData.endHeight,
                            isCancelEnabled = true,
                            titleRange = R.string.profile_filter_height_value,
                            valueRange = DEFAULT_HEIGHT_RANGE,
                            onOptionSelected = { start, end ->
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(
                                    FilterChangeAction.HeightFilter(
                                        start = start,
                                        end = end
                                    )
                                )
                            },
                            onCancel = {
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                            }
                        )
                    }

                    FilterProfileOption.ZODIAC -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_80),
                            selectedOptions = state.filterData.filterZodiacs,
                            options = FilterZodiac.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterZodiac
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.ZodiacFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.ALCOHOL -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_50),
                            selectedOptions = state.filterData.filterAlcohols,
                            options = FilterAlcohol.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterAlcohol
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.AlcoholFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.SMOKE -> {
                        FilterSingleSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_30),
                            selectedOption = stringResource(id = state.filterData.filterSmoke.title),
                            options = FilterSmoke.entries.filter { it != FilterSmoke.NOT_SET },
                            onOptionSelected = { value ->
                                val result = value as FilterSmoke
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.SmokeFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.CHARACTER -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_40),
                            selectedOptions = state.filterData.filterCharacter,
                            options = FilterCharacter.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterCharacter
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.CharacterFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.RELIGION -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_80),
                            selectedOptions = state.filterData.filterReligions,
                            options = FilterReligion.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterReligion
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.ReligionFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.LANGUAGES -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_80),
                            selectedOptions = state.filterData.filterLanguages,
                            options = FilterLanguage.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterLanguage
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.LanguagesFilter(result))
                            }
                        )
                    }

                    FilterProfileOption.PETS -> {
                        FilterMultiSelector(
                            modifier = Modifier.fillMaxHeight(SCALE_80),
                            selectedOptions = state.filterData.filterPets,
                            options = FilterPets.entries,
                            onOptionSelected = { value ->
                                val result = value.map {
                                    it as FilterPets
                                }
                                isFilterEditOpen.value = false
                                filterType = FilterProfileOption.NONE
                                onChangeAction(FilterChangeAction.PetsFilter(result))
                            }
                        )
                    }

                    else -> {

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDatingFilterView() {
    DatingAppTheme {
        DatingFilterView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewDatingFilterView() {
    DatingAppTheme(darkTheme = true) {
        DatingFilterView()
    }
}