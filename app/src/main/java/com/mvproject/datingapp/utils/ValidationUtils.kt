/*
 * Create by Medvediev Viktor
 * last update: 15.06.23, 19:03
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.utils

import android.text.TextUtils
import android.widget.EditText
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale

fun String.isEmpty(): Boolean {
    return (TextUtils.isEmpty(this)
            || this.equals("", ignoreCase = true)
            || this.equals("{}", ignoreCase = true)
            || this.equals("null", ignoreCase = true)
            || this.equals("undefined", ignoreCase = true))
}

fun EditText.isValidEmail(): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return !this.text.toString().isEmpty() && this.text.toString().matches(emailPattern)
}

fun String.isValidEmail(): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return !this.isEmpty() && this.matches(emailPattern)
}

fun String.isValidPassword(): String? {
    val passwordText = this
    if (passwordText.length < 8) {
        return "Minimum 8 Character Password"
    }
    /*    if(!passwordText.matches(".*[A-Z].*".toRegex())) {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex())) {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }*/

    return null
}

fun String.toDatePattern(): String {
    return if (this.length == 8) {
        StringBuilder(this).apply {
            insert(4, '/')
            insert(2, '/')
        }.toString()
    } else this
}

fun String.isDateValid(): Boolean {
    return if (this.length > 2) {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            val dateParse = formatter.parse(this)
            Timber.w("testing from:$this parsed date: $dateParse")
            true
        } catch (ex: Exception) {
            Timber.w("testing $this not parse ${ex.localizedMessage}")
            false
        }
    } else true

}