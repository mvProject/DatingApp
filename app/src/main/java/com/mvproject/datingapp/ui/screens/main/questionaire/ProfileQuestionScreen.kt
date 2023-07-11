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
import com.mvproject.datingapp.ui.screens.main.questionaire.action.ProfileQuestionsAction
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsDataState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState
import com.mvproject.datingapp.ui.screens.main.questionaire.state.ProfileQuestionsState.Companion.isStartState
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens
import com.mvproject.datingapp.utils.WEIGHT_1
import timber.log.Timber

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

        val pageTitle = when (state.currentStep) {
            ProfileQuestionsState.START -> R.string.questionnaire_start_title
            ProfileQuestionsState.ABOUT -> R.string.questionnaire_about_title
            ProfileQuestionsState.ORIENTATION -> R.string.questionnaire_orientation_title
            ProfileQuestionsState.MARITAL_STATUS -> R.string.questionnaire_marital_status_title
            ProfileQuestionsState.CHILDREN -> R.string.questionnaire_children_title
            ProfileQuestionsState.HEIGHT -> R.string.questionnaire_height_title
            ProfileQuestionsState.ZODIAC -> R.string.questionnaire_zodiac_title
            ProfileQuestionsState.ALCOHOL -> R.string.questionnaire_alcohol_title
            ProfileQuestionsState.SMOKE -> R.string.questionnaire_smoke_title
            ProfileQuestionsState.PSY_ORIENTATION -> R.string.questionnaire_vert_title
            ProfileQuestionsState.RELIGION -> R.string.questionnaire_religion_title
            ProfileQuestionsState.LANGUAGES -> R.string.questionnaire_languages_title
            ProfileQuestionsState.PETS -> R.string.questionnaire_pets_title
            ProfileQuestionsState.WORK -> R.string.questionnaire_work_title
            ProfileQuestionsState.END -> R.string.questionnaire_end_title
        }

        val pageLogo = when (state.currentStep) {
            ProfileQuestionsState.START -> R.drawable.questions_start
            ProfileQuestionsState.ABOUT -> R.drawable.questions_about
            ProfileQuestionsState.ORIENTATION -> R.drawable.questions_orientation
            ProfileQuestionsState.MARITAL_STATUS -> R.drawable.questions_marital_status
            ProfileQuestionsState.CHILDREN -> R.drawable.questions_children
            ProfileQuestionsState.HEIGHT -> R.drawable.questions_height
            ProfileQuestionsState.ZODIAC -> R.drawable.questions_zodiac
            ProfileQuestionsState.ALCOHOL -> R.drawable.questions_alcohol
            ProfileQuestionsState.SMOKE -> R.drawable.questions_smoke
            ProfileQuestionsState.PSY_ORIENTATION -> R.drawable.questions_vert
            ProfileQuestionsState.RELIGION -> R.drawable.questions_religion
            ProfileQuestionsState.LANGUAGES -> R.drawable.questions_languages
            ProfileQuestionsState.PETS -> R.drawable.questions_pets
            ProfileQuestionsState.WORK -> R.drawable.questions_work
            ProfileQuestionsState.END -> R.drawable.questions_end
        }

        val navLogo = if (state.currentStep == ProfileQuestionsState.START)
            R.drawable.ic_close
        else
            R.drawable.ic_navigate_back

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
                                    painter = painterResource(id = navLogo),
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
                    .padding(
                        horizontal = MaterialTheme.dimens.size24
                    )
                    .padding(
                        bottom = MaterialTheme.dimens.size16
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val painter = rememberAsyncImagePainter(model = pageLogo)

                when (state.currentStep) {
                    ProfileQuestionsState.START -> {
                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size38))

                        Image(
                            modifier = Modifier
                                .size(MaterialTheme.dimens.size192),
                            painter = painter,
                            contentDescription = state.currentStep.toString()
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size38))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = pageTitle),
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

                        Spacer(modifier = Modifier.weight(WEIGHT_1))

                        GradientButton(
                            modifier = Modifier.fillMaxWidth(),
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
                            text = stringResource(id = pageTitle),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.headlineLarge,
                            fontSize = MaterialTheme.dimens.font22
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                        Text(
                            text = stringResource(id = R.string.questionnaire_end_description),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.weight(WEIGHT_1))

                        GradientButton(
                            modifier = Modifier.fillMaxWidth(),
                            title = stringResource(id = R.string.btn_title_ok),
                            onClick = onNavigateNext
                        )
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
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = pageTitle),
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.headlineLarge,
                            fontSize = MaterialTheme.dimens.font22
                        )

                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16))

                when (state.currentStep) {
                    ProfileQuestionsState.ABOUT -> {
                        AboutInput() { text ->
                            Timber.w("testing about $text")
                            onAction(ProfileQuestionsAction.UpdateProfileAbout(text))
                            onAction(ProfileQuestionsAction.NextStep)
                        }
                    }

                    ProfileQuestionsState.ORIENTATION -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.MARITAL_STATUS -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.CHILDREN -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.HEIGHT -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.ZODIAC -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
                    }

                    ProfileQuestionsState.ALCOHOL -> {
                        Button(onClick = {
                            onAction(ProfileQuestionsAction.NextStep)
                        }) {
                            Text(state.currentStep.toString())
                        }
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