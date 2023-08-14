package com.mvproject.datingapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimens(
    val sizeZero: Dp = 0.dp,
    val size1: Dp = 1.dp,
    val size2: Dp = 2.dp,
    val size3: Dp = 3.dp,
    val size4: Dp = 4.dp,
    val size5: Dp = 5.dp,
    val size6: Dp = 6.dp,
    val size8: Dp = 8.dp,
    val size10: Dp = 10.dp,
    val size11: Dp = 11.dp,
    val size12: Dp = 12.dp,
    val size14: Dp = 14.dp,
    val size15: Dp = 15.dp,
    val size16: Dp = 16.dp,
    val size17: Dp = 17.dp,
    val size18: Dp = 18.dp,
    val size20: Dp = 20.dp,
    val size22: Dp = 22.dp,
    val size24: Dp = 24.dp,
    val size26: Dp = 26.dp,
    val size28: Dp = 28.dp,
    val size30: Dp = 30.dp,
    val size32: Dp = 32.dp,
    val size34: Dp = 34.dp,
    val size38: Dp = 38.dp,
    val size40: Dp = 40.dp,
    val size42: Dp = 42.dp,
    val size44: Dp = 44.dp,
    val size46: Dp = 46.dp,
    val size48: Dp = 48.dp,
    val size50: Dp = 50.dp,
    val size52: Dp = 52.dp,
    val size60: Dp = 60.dp,
    val size62: Dp = 62.dp,
    val size64: Dp = 64.dp,
    val size74: Dp = 74.dp,
    val size78: Dp = 78.dp,
    val size82: Dp = 82.dp,
    val size80: Dp = 80.dp,
    val size88: Dp = 88.dp,
    val size90: Dp = 90.dp,
    val size96: Dp = 96.dp,
    val size100: Dp = 100.dp,
    val size120: Dp = 120.dp,
    val size126: Dp = 126.dp,
    val size128: Dp = 128.dp,
    val size130: Dp = 130.dp,
    val size156: Dp = 156.dp,
    val size168: Dp = 168.dp,
    val size174: Dp = 174.dp,
    val size180: Dp = 180.dp,
    val size192: Dp = 192.dp,
    val size200: Dp = 200.dp,
    val size220: Dp = 220.dp,
    val size250: Dp = 250.dp,
    val size286: Dp = 286.dp,
    val size360: Dp = 360.dp,
    val size420: Dp = 420.dp,

    val font10: TextUnit = 10.sp,
    val font11: TextUnit = 11.sp,
    val font12: TextUnit = 12.sp,
    val font14: TextUnit = 14.sp,
    val font16: TextUnit = 16.sp,
    val font17: TextUnit = 17.sp,
    val font18: TextUnit = 18.sp,
    val font20: TextUnit = 20.sp,
    val font22: TextUnit = 22.sp,
    val font24: TextUnit = 24.sp,
    val font32: TextUnit = 32.sp,

    val weight1: Float = 1f,
    val weight2: Float = 2f,
    val weight3: Float = 3f,
    val weight4: Float = 4f,
    val weight5: Float = 5f,
    val weight6: Float = 6f,
    val weight10: Float = 10f,

    val fraction50: Float = 0.5f,
    val fraction70: Float = 0.7f,
    val fraction80: Float = 0.8f,

    val alpha10: Float = 0.1f,
    val alpha20: Float = 0.2f,
    val alpha30: Float = 0.3f,
    val alpha50: Float = 0.5f,
    val alpha60: Float = 0.6f,
    val alpha70: Float = 0.7f,
    val alpha80: Float = 0.8f,
    val alphaDefault: Float = 1f

)

internal val LocalDimens = staticCompositionLocalOf { Dimens() }

val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current
