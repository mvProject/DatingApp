/*
 * Create by Medvediev Viktor
 * last update: 07.06.23, 12:32
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mvproject.datingapp.ui.screens.NavGraphs
import com.mvproject.datingapp.ui.screens.main.viewmodel.MainViewModel
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // todo add proper logo
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }

        setContent {
            DatingAppTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}