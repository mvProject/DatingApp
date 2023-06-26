/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.home.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.navigation.NavigationHost
import com.mvproject.datingapp.ui.screens.main.home.viewmodel.HomeViewModel
import com.mvproject.datingapp.ui.theme.DatingAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberAnimatedNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    val startScreen by remember(authState) {
        derivedStateOf {
            if (authState)
                AppRoutes.PROFILE.route
            else
                AppRoutes.SignIn.route
        }
    }

    Scaffold(
        bottomBar = {
            // todo turn on
            //  AppBottomNavigation(
            //      navController = navController,
            //      bottomNavigationItems = AppRoutes.BottomBar.routes,
            //      onDestinationClick = navController::bottomNavigateToRoute
            //  )
        }
    ) { paddingValues ->

        NavigationHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = startScreen
        )
    }
}

@Composable
fun AppBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomNavigationItems: List<AppRoutes>,
    onDestinationClick: (String) -> Unit = {}
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isBottomBarVisible = bottomNavigationItems
        .any { it.route == currentDestination?.route }

    if (isBottomBarVisible) {
        NavigationBar(
            modifier = modifier
        ) {
            bottomNavigationItems.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(Icons.Default.List, contentDescription = null)
                    },
                    label = {
                        Text(
                            text = screen.route
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.tertiary,
                        selectedTextColor = MaterialTheme.colorScheme.tertiary,
                        unselectedIconColor = MaterialTheme.colorScheme.inverseSurface,
                        unselectedTextColor = MaterialTheme.colorScheme.inverseSurface,
                        indicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    selected = screen.route == currentDestination?.route,
                    onClick = { onDestinationClick(screen.route) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    DatingAppTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreviewProfileView() {
    DatingAppTheme(darkTheme = true) {
        HomeScreen()
    }
}