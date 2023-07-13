/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 14:42
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.model

data class UserHeight(
    val height: Int = 170,
    val isHeightNotVisible: Boolean = false
) {
    override fun toString(): String {
        return "$height;$isHeightNotVisible"
    }

    companion object {
        fun fromString(s: String): UserHeight {
            val splitted = s.split(";")
            return UserHeight(
                height = splitted[0].toInt(),
                isHeightNotVisible = splitted[1].toBoolean(),
            )
        }
    }
}