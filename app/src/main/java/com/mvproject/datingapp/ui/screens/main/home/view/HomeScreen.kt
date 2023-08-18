/*
 * Create by Medvediev Viktor
 * last update: 26.06.23, 12:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.screens.main.home.view

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mvproject.datingapp.navigation.AppRoutes
import com.mvproject.datingapp.navigation.BottomNavItem
import com.mvproject.datingapp.navigation.NavigationHost
import com.mvproject.datingapp.navigation.bottomNavigateToRoute
import com.mvproject.datingapp.ui.screens.main.home.viewmodel.HomeViewModel
import com.mvproject.datingapp.ui.screens.main.profile.view.navigation.Profile
import com.mvproject.datingapp.ui.theme.DatingAppTheme
import com.mvproject.datingapp.ui.theme.dimens

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    val routesState by viewModel.routes.collectAsStateWithLifecycle()

    val startScreen by remember(authState) {
        derivedStateOf {
            if (authState)
                Profile.route
            else
                AppRoutes.SignIn.route
        }
    }

    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                navController = navController,
                bottomNavigationItems = routesState,
                onDestinationClick = navController::bottomNavigateToRoute
            )
        }
    ) { _ ->
        NavigationHost(
            navController = navController,
            startDestination = startScreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomNavigationItems: List<BottomNavItem>,
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
                val isSelected = screen.route == currentDestination?.route
                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                                if (screen.isWithBadge) {
                                    Badge(containerColor = MaterialTheme.colorScheme.tertiary)
                                }
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = screen.logo),
                                contentDescription = screen.route
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = screen.title),
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = MaterialTheme.dimens.font10
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.secondary,
                        selectedTextColor = MaterialTheme.colorScheme.secondary,
                        unselectedIconColor = MaterialTheme.colorScheme.inverseSurface,
                        unselectedTextColor = MaterialTheme.colorScheme.inverseSurface,
                        indicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    selected = isSelected,
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