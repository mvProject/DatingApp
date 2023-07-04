/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 18:47
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException


fun Context.savePhotoToInternalStorage(uri: Uri, filename: String): Boolean {
    return try {
        contentResolver.openInputStream(uri).use { input ->
            val bitmap = BitmapFactory.decodeStream(input)
            openFileOutput("$filename.jpg", MODE_PRIVATE).use { stream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                    throw IOException("Couldn't save bitmap.")
                }
            }
        }
        true
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }
}

data class InternalStoragePhoto(
    val name: String,
    val bmp: Bitmap
)

suspend fun Context.loadPhotosFromInternalStorage(): List<InternalStoragePhoto> {
    return withContext(Dispatchers.IO) {
        val files = filesDir.listFiles()
        files?.filter { it.canRead() && it.isFile && it.name.endsWith(".jpg") }?.map {
            val bytes = it.readBytes()
            val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            InternalStoragePhoto(it.name, bmp)
        } ?: listOf()
    }
}

suspend fun Context.loadPhotosUrisFromInternalStorage(): List<Uri> {
    return withContext(Dispatchers.IO) {
        val files = filesDir.listFiles()
        return@withContext files
            ?.filter { it.canRead() && it.isFile && it.name.endsWith(".jpg") }
            ?.map {
                it.toUri()
            }
            ?: listOf()
    }
}

fun Context.deletePhotoFromInternalStorage(filename: String): Boolean {
    return try {
        deleteFile(filename)
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}