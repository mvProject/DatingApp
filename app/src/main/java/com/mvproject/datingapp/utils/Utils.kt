/*
 * Create by Medvediev Viktor
 * last update: 12.06.23, 19:50
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.utils

import android.net.Uri
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.pow

fun Modifier.gradientBackground(colors: List<Color>, angle: Float = 0f) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * PI
        val x = kotlin.math.cos(angleRad).toFloat() //Fractional x
        val y = kotlin.math.sin(angleRad).toFloat() //Fractional y

        val radius: Float = kotlin.math.sqrt(((size.width.pow(2) + size.height.pow(2))) / 2f)
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = kotlin.math.min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - kotlin.math.min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)

fun String.emailToFileName() = this.replace('@', '_')

fun Uri?.notNullOrEmpty(): Boolean {
    return this != null && this != Uri.EMPTY
}

fun prepareImages(images: List<String> = emptyList()): List<String> {
    val count = images.count()
    val neededCount = PHOTO_MAX_COUNT - count
    return buildList {
        images.forEach {
            add(it)
        }
        repeat(neededCount) {
            add(STRING_EMPTY)
        }
    }.sortedBy { it.isEmpty() }
}
