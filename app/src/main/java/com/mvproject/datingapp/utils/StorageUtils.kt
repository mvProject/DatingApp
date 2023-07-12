/*
 * Create by Medvediev Viktor
 * last update: 04.07.23, 18:47
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.math.min

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
        Timber.e("testing deletePhotoFromInternalStorage ${e.message}")
        false
    }
}

@SuppressLint("Recycle")
fun Context.getRealPathFromURI(contentUri: Uri, filename: String): String? {
    val returnCursor = contentResolver.query(
        contentUri, null, null, null, null
    )
    val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
    returnCursor.moveToFirst()
    val size = returnCursor.getLong(sizeIndex).toString()
    val file = File(filesDir, filename)
    try {
        val inputStream: InputStream? = contentResolver.openInputStream(contentUri)
        val outputStream = FileOutputStream(file)
        var read = 0
        val maxBufferSize = 1 * 1024 * 1024
        val bytesAvailable: Int = inputStream?.available() ?: 0
        //int bufferSize = 1024;
        val bufferSize = min(bytesAvailable, maxBufferSize)
        val buffers = ByteArray(bufferSize)
        while (inputStream?.read(buffers).also {
                if (it != null) {
                    read = it
                }
            } != -1) {
            outputStream.write(buffers, 0, read)
        }
        inputStream?.close()
        outputStream.close()
        Timber.w("testing getRealPathFromURI path:${file.path}, size:${file.length()}")
    } catch (e: java.lang.Exception) {
        Timber.e("testing getRealPathFromURI ${e.message}")
    }
    return file.path
}