/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 11:44
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun backColor(isSelected: Boolean) = if (isSelected) {
    MaterialTheme.colorScheme.outlineVariant
} else {
    MaterialTheme.colorScheme.primary
}

@Composable
fun borderColor(isSelected: Boolean) = if (isSelected) {
    MaterialTheme.colorScheme.secondaryContainer
} else {
    MaterialTheme.colorScheme.outline
}

@Composable
fun textColor(isSelected: Boolean) = if (isSelected) {
    MaterialTheme.colorScheme.secondaryContainer
} else {
    MaterialTheme.colorScheme.onPrimary
}