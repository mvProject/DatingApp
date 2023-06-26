/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 10:51
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.mvproject.datingapp.ui.screens.main.home.view.HomeScreen
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }

        setContent {
            DatingAppTheme {
                HomeScreen()
            }
        }
    }
}