/*
 * Create by Medvediev Viktor
 * last update: 19.06.23, 15:00
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.questionaire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mvproject.datingapp.ui.theme.DatingAppTheme

@Composable
fun ProfileQuestionScreen(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<ProfileQuestionViewModel>()

    ProfileQuestionView()
}

@Composable
fun ProfileQuestionView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("SignUpScreen", textAlign = TextAlign.Center)
        Button(onClick = {

        }) {
            Text("Go to Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileQuestionView() {
    DatingAppTheme {
        ProfileQuestionView()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfileQuestionView() {
    DatingAppTheme(darkTheme = true) {
        ProfileQuestionView()
    }
}