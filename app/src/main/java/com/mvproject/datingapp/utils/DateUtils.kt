/*
 * Create by Medvediev Viktor
 * last update: 03.07.23, 17:34
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.utils

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun toDateMillis(birthDate: String): Long? {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    dateFormat.isLenient = false // Disables lenient parsing

    try {
        val parsedBirthDate = dateFormat.parse(birthDate)
        val birthCalendar = Calendar.getInstance()
        birthCalendar.time = parsedBirthDate

        // Perform additional validation
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val birthYear = birthCalendar.get(Calendar.YEAR)
        val birthMonth = birthCalendar.get(Calendar.MONTH)
        val birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH)

        if (birthYear > currentYear) {
            Timber.e("testing Invalid birth year")
            return null
        }

        if (birthMonth > 11) { // 11 represents December (0-indexed)
            Timber.e("testing Invalid birth month")
            return null
        }

        val maxDaysInMonth = birthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        if (birthDay > maxDaysInMonth) {
            Timber.e("testing Invalid birth day")
            return null
        }

        return birthCalendar.timeInMillis
    } catch (e: Exception) {
        throw IllegalArgumentException("Invalid date format")
    }
}

fun isValidDateMillis(millis: Long): Boolean {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val birthCalendar = Calendar.getInstance()
    birthCalendar.timeInMillis = millis
    val birthYear = birthCalendar.get(Calendar.YEAR)
    val birthMonth = birthCalendar.get(Calendar.MONTH)
    val birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH)

    if (birthYear > currentYear) {
        return false
    }

    if (birthMonth > 11) { // 11 represents December (0-indexed)
        return false
    }

    val maxDaysInMonth = birthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    if (birthDay > maxDaysInMonth) {
        return false
    }
    return true
}

fun calculatAgeMillis(millis: Long): Int {
    // Calculate age
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val birthCalendar = Calendar.getInstance()
    birthCalendar.timeInMillis = millis
    val birthYear = birthCalendar.get(Calendar.YEAR)

    var age = currentYear - birthYear

    if (Calendar.getInstance()
            .get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)
    ) {
        age--
    }

    return age
}

fun calculateAge(birthDate: String): Int {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    dateFormat.isLenient = false // Disables lenient parsing

    try {
        val parsedBirthDate = dateFormat.parse(birthDate)
        val birthCalendar = Calendar.getInstance()
        birthCalendar.time = parsedBirthDate

        // Perform additional validation
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val birthYear = birthCalendar.get(Calendar.YEAR)
        val birthMonth = birthCalendar.get(Calendar.MONTH)
        val birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH)

        if (birthYear > currentYear) {
            throw IllegalArgumentException("Invalid birth year")
        }

        if (birthMonth > 11) { // 11 represents December (0-indexed)
            throw IllegalArgumentException("Invalid birth month")
        }

        val maxDaysInMonth = birthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        if (birthDay > maxDaysInMonth) {
            throw IllegalArgumentException("Invalid birth day")
        }

        // Calculate age
        var age = currentYear - birthYear

        if (Calendar.getInstance()
                .get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)
        ) {
            age--
        }

        return age
    } catch (e: Exception) {
        throw IllegalArgumentException("Invalid date format")
    }
}


fun Long.convertDateToReadableFormat(): String {
    return if (this == LONG_ZERO) STRING_EMPTY else
        SimpleDateFormat(
            "dd/MM/yyyy",
            Locale.getDefault()
        ).format(this)
}

fun Long.convertDateToClearFormat(): String {
    return if (this == LONG_ZERO) STRING_EMPTY else
        SimpleDateFormat(
            "ddMMyyyy",
            Locale.getDefault()
        ).format(this)
}