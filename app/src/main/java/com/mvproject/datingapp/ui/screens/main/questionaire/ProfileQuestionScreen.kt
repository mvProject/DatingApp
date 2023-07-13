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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.mvproject.datingapp.ui.components.selectors.AlcoholSelector
import com.mvproject.datingapp.ui.components.selectors.ChildrenSelector
import com.mvproject.datingapp.ui.components.selectors.HeightSelector
import com.mvproject.datingapp.ui.components.selectors.MaritalSelector
import com.mvproject.datingapp.ui.components.selectors.OrientationSelector
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

@Composable
fun ProfileQuestionScreen(
    viewModel: ProfileQuestionViewModel,
    onNavigateBack: () -> Unit,
    onNavigateNext: () -> Unit,
) {
    val state by viewModel.profileQuestionsDataState.collectAsStateWithLifecycle()

    ProfileQuestionView(
        state = state,
        onAction = viewModel::processAction,
        onNavigateBack = onNavigateBack,
        onNavigateNext = onNavigateNext
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileQuestionView(
    state: ProfileQuestionsDataState,
    onAction: (ProfileQuestionsAction) -> Unit = {},
    onNavigateBack: () -> Unit = {},
    onNavigateNext: () -> Unit = {}
) {
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
                    .imePadding()
                    .padding(paddingValues)
                    .fillMaxSize()
                    // .padding(
                    //     horizontal = MaterialTheme.dimens.size24
                    // )
                    .padding(
                        start = MaterialTheme.dimens.size16,
                        end = MaterialTheme.dimens.size16,
                        bottom = MaterialTheme.dimens.size16
                    ),
                verticalArrangement = Arrangement.Top,
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
                            onClick = onNavigateNext
                        )
                    }

                    ProfileQuestionsState.ABOUT -> {
                        AboutInput(
                            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.size8),
                            title = stringResource(state.currentStep.stateTitle()),
                            logo = state.currentStep.stateLogo(),
                        ) { text ->
                            onAction(ProfileQuestionsAction.UpdateProfileAbout(text))
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
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.PSY_ORIENTATION -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.RELIGION -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.LANGUAGES -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.PETS -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.WORK -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
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

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfileQuestionView() {
    DatingAppTheme(darkTheme = true) {
        ProfileQuestionView(ProfileQuestionsDataState())
    }
}