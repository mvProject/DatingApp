/*
 * Create by Medvediev Viktor
 * last update: 19.06.23, 15:00
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.mvproject.datingapp.R
import com.mvproject.datingapp.ui.components.buttons.GradientButton
import com.mvproject.datingapp.ui.components.input.about.AboutInput
import com.mvproject.datingapp.ui.components.input.work.WorkInput
import com.mvproject.datingapp.ui.components.selectors.AlcoholSelector
import com.mvproject.datingapp.ui.components.selectors.ChildrenSelector
import com.mvproject.datingapp.ui.components.selectors.HeightSelector
import com.mvproject.datingapp.ui.components.selectors.LanguagesSelector
import com.mvproject.datingapp.ui.components.selectors.MaritalSelector
import com.mvproject.datingapp.ui.components.selectors.OrientationSelector
import com.mvproject.datingapp.ui.components.selectors.PetSelector
import com.mvproject.datingapp.ui.components.selectors.PsyOrientationSelector
import com.mvproject.datingapp.ui.components.selectors.ReligionSelector
import com.mvproject.datingapp.ui.components.selectors.SmokeSelector
import com.mvproject.datingapp.ui.components.selectors.ZodiacSelector
import com.mvproject.datingapp.ui.screens.main.questionaire.action.ProfileQuestionsAction
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsDataState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.isStartState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.stateLogo
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.stateNavLogo
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.stateTitle
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1
import com.mvproject.datingapp.utils.WEIGHT_3
import timber.log.Timber

@Composable
fun ProfileQuestionScreen(
    viewModel: ProfileQuestionViewModel,
    onNavigateNext: () -> Unit,
) {
    val state by viewModel.profileQuestionsDataState.collectAsStateWithLifecycle()

    ProfileQuestionView(
        state = state,
        onAction = viewModel::processAction,
        onNavigateNext = onNavigateNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileQuestionView(
    state: ProfileQuestionsDataState,
    onAction: (ProfileQuestionsAction) -> Unit = {},
    onNavigateNext: () -> Unit = {}
) {
    LaunchedEffect(key1 = state) {
        if (state.isComplete) {
            Timber.w("testing is ProfileQuestionView complete")
            onNavigateNext()
        }
    }

    BackHandler(true) {
        if (state.currentStep.isStartState()) {
            onNavigateNext()
        } else {
            onAction(ProfileQuestionsAction.PrevStep)
        }
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        if (state.currentStep != ProfileQuestionsState.START && state.currentStep != ProfileQuestionsState.END) {
            val progress = animateFloatAsState(
                targetValue = state.currentStepProgress
            )

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.size2),
                color = MaterialTheme.colorScheme.secondaryContainer,
                trackColor = MaterialTheme.colorScheme.outline,
                progress = progress.value
            )
        }

        Scaffold(
            topBar = {
                if (state.currentStep != ProfileQuestionsState.END) {
                    CenterAlignedTopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    if (state.currentStep.isStartState()) {
                                        onNavigateNext()
                                    } else {
                                        onAction(ProfileQuestionsAction.PrevStep)
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = state.currentStep.stateNavLogo()),
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(
                        start = MaterialTheme.dimens.size16,
                        end = MaterialTheme.dimens.size16,
                        bottom = MaterialTheme.dimens.size16
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val painter = rememberAsyncImagePainter(model = state.currentStep.stateLogo())

                when (state.currentStep) {
                    ProfileQuestionsState.START -> {
                        Spacer(modifier = Modifier.weight(WEIGHT_1))

                        Image(
                            modifier = Modifier
                                .size(MaterialTheme.dimens.size192),
                            painter = painter,
                            contentDescription = state.currentStep.toString()
                        )

                        Spacer(modifier = Modifier.weight(WEIGHT_1))

                        Text(
                            modifier = Modifier
                                .padding(horizontal = MaterialTheme.dimens.size8),
                            text = stringResource(id = state.currentStep.stateTitle()),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.displayLarge,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                        Text(
                            text = stringResource(id = R.string.questionnaire_start_description),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.weight(WEIGHT_3))

                        GradientButton(
                            modifier = Modifier
                                .padding(horizontal = MaterialTheme.dimens.size8),
                            title = stringResource(id = R.string.btn_title_start),
                            onClick = { onAction(ProfileQuestionsAction.NextStep) }
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                        Text(
                            modifier = Modifier
                                .clickable {
                                    onNavigateNext()
                                }
                                .padding(
                                    horizontal = MaterialTheme.dimens.size20,
                                    vertical = MaterialTheme.dimens.size10
                                ),
                            text = stringResource(id = R.string.btn_title_not_now),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    ProfileQuestionsState.END -> {
                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size60))

                        Image(
                            modifier = Modifier
                                .size(
                                    width = MaterialTheme.dimens.size286,
                                    height = MaterialTheme.dimens.size168
                                ),
                            painter = painter,
                            contentDescription = state.currentStep.toString()
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size48))

                        Text(
                            text = stringResource(id = state.currentStep.stateTitle()),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.headlineLarge,
                            fontSize = MaterialTheme.dimens.font22
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                        Text(
                            modifier = Modifier
                                .padding(horizontal = MaterialTheme.dimens.size8),
                            text = stringResource(id = R.string.questionnaire_end_description),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.weight(WEIGHT_1))

                        GradientButton(
                            modifier = Modifier
                                .padding(horizontal = MaterialTheme.dimens.size8),
                            title = stringResource(id = R.string.btn_title_ok),
                            onClick = {
                                onAction(ProfileQuestionsAction.SaveProfileInfo)
                            }
                        )
                    }

                    ProfileQuestionsState.ABOUT -> {
                        AboutInput(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            initial = state.profileAbout,
                            title = stringResource(state.currentStep.stateTitle()),
                            logo = state.currentStep.stateLogo(),
                        ) { text ->
                            onAction(ProfileQuestionsAction.UpdateProfileAbout(text))
                        }
                    }

                    ProfileQuestionsState.WORK -> {
                        WorkInput(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            initial = state.userWork,
                            title = stringResource(state.currentStep.stateTitle()),
                            logo = state.currentStep.stateLogo(),
                        ) { workInfo ->
                            onAction(ProfileQuestionsAction.UpdateProfileWork(workInfo))
                        }
                    }

                    else -> {
                        Image(
                            modifier = Modifier
                                .size(MaterialTheme.dimens.size200),
                            painter = painter,
                            contentDescription = state.currentStep.toString()
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size24))

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MaterialTheme.dimens.size8),
                            text = stringResource(id = state.currentStep.stateTitle()),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.headlineLarge,
                            fontSize = MaterialTheme.dimens.font22
                        )
                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                when (state.currentStep) {
                    ProfileQuestionsState.ORIENTATION -> {
                        OrientationSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOption = stringResource(id = state.profileOrientation.title),
                            onOptionSelected = { orientation ->
                                onAction(ProfileQuestionsAction.UpdateProfileOrientation(orientation))
                            }
                        )
                    }

                    ProfileQuestionsState.MARITAL_STATUS -> {
                        MaritalSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOption = stringResource(id = state.profileMarital.title),
                            onOptionSelected = { status ->
                                onAction(ProfileQuestionsAction.UpdateProfileMarital(status))
                            }
                        )
                    }

                    ProfileQuestionsState.CHILDREN -> {
                        ChildrenSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOption = stringResource(id = state.profileChildren.title),
                            onOptionSelected = { status ->
                                onAction(ProfileQuestionsAction.UpdateProfileChildren(status))
                            }
                        )
                    }

                    ProfileQuestionsState.HEIGHT -> {
                        HeightSelector(
                            initial = state.userHeight,
                            onOptionSelected = { userHeight ->
                                onAction(ProfileQuestionsAction.UpdateProfileHeight(userHeight))
                            }
                        )
                    }

                    ProfileQuestionsState.ZODIAC -> {
                        ZodiacSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOption = stringResource(id = state.profileZodiac.title),
                            onOptionSelected = { sign ->
                                onAction(ProfileQuestionsAction.UpdateProfileZodiac(sign))
                            }
                        )
                    }

                    ProfileQuestionsState.ALCOHOL -> {
                        AlcoholSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOption = stringResource(id = state.profileAlcohol.title),
                            onOptionSelected = { status ->
                                onAction(ProfileQuestionsAction.UpdateProfileAlcohol(status))
                            }
                        )
                    }

                    ProfileQuestionsState.SMOKE -> {
                        SmokeSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOption = stringResource(id = state.profileSmoke.title),
                            onOptionSelected = { status ->
                                onAction(ProfileQuestionsAction.UpdateProfileSmoke(status))
                            }
                        )
                    }

                    ProfileQuestionsState.PSY_ORIENTATION -> {
                        PsyOrientationSelector(
                            selectedOption = stringResource(id = state.profilePsyOrientation.title),
                            onOptionSelected = { orientation ->
                                onAction(
                                    ProfileQuestionsAction.UpdateProfilePsyOrientation(
                                        orientation
                                    )
                                )
                            }
                        )
                    }

                    ProfileQuestionsState.RELIGION -> {
                        ReligionSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOption = stringResource(id = state.profileReligion.title),
                            onOptionSelected = { religion ->
                                onAction(ProfileQuestionsAction.UpdateProfileReligion(religion))
                            }
                        )
                    }

                    ProfileQuestionsState.LANGUAGES -> {
                        LanguagesSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOptions = state.profileLanguages,
                            onOptionSelected = { languages ->
                                onAction(ProfileQuestionsAction.UpdateProfileLanguages(languages))
                            }
                        )
                    }

                    ProfileQuestionsState.PETS -> {
                        PetSelector(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            selectedOptions = state.profilePets,
                            onOptionSelected = { pets ->
                                onAction(ProfileQuestionsAction.UpdateProfilePets(pets))
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
fun PreviewProfileQuestionView() {
    DatingAppTheme {
        ProfileQuestionView(ProfileQuestionsDataState())
    }
}