/*
 * Create by Medvediev Viktor
 * last update: 07.06.23, 12:03
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = white,
    onPrimary = black,
    primaryContainer = black,
    onPrimaryContainer = white,
    secondary = blueviolet,
    onSecondary = black,
    secondaryContainer = bluevioletDark,
    onSecondaryContainer = black,
    tertiary = hotpink,
    onTertiary = white,
    tertiaryContainer = lightcoral,
    onTertiaryContainer = white,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = crimson,
    onErrorContainer = tomato,
    background = white,
    onBackground = black,
    surface = white,
    onSurface = graydark,
    surfaceVariant = whitesmoke,
    onSurfaceVariant = graylight,
    outline = grayverylite,
    inverseOnSurface = darkslategray,
    inverseSurface = graydark,
    inversePrimary = darkslateblue,
    surfaceTint = graywhite,
    outlineVariant = ghostwhite,
    scrim = md_theme_light_scrim
)

private val DarkColorScheme = darkColorScheme(
    primary = white,
    onPrimary = black,
    primaryContainer = black,
    onPrimaryContainer = white,
    secondary = blueviolet,
    onSecondary = black,
    secondaryContainer = bluevioletDark,
    onSecondaryContainer = black,
    tertiary = hotpink,
    onTertiary = white,
    tertiaryContainer = lightcoral,
    onTertiaryContainer = white,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = crimson,
    onErrorContainer = tomato,
    background = white,
    onBackground = black,
    surface = white,
    onSurface = graydark,
    surfaceVariant = whitesmoke,
    onSurfaceVariant = graylight,
    outline = grayverylite,
    inverseOnSurface = darkslategray,
    inverseSurface = graydark,
    inversePrimary = darkslateblue,
    surfaceTint = graywhite,
    outlineVariant = ghostwhite,
    scrim = md_theme_light_scrim
)

@Composable
fun DatingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = true
    )

    CompositionLocalProvider(
        LocalDimens provides Dimens(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = shapes,
            content = content
        )
    }
}