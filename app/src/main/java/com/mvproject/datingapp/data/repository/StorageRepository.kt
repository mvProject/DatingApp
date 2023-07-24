/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 16:43
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.data.repository

import android.content.Context
import android.net.Uri
import com.mvproject.datingapp.utils.STRING_EMPTY
import com.mvproject.datingapp.utils.deletePhotoFromInternalStorage
import com.mvproject.datingapp.utils.emailToFileName
import com.mvproject.datingapp.utils.getRealPathFromURI
import com.mvproject.datingapp.utils.loadPhotosUrisFromInternalStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun setUserPhotos(username: String, source: List<String>): List<String> {
        val result = buildList {
            withContext(Dispatchers.IO) {
                source.forEach { uriStr ->
                    if (uriStr.isNotEmpty()) {
                        val uri = Uri.parse(uriStr)
                        val uuid = UUID.randomUUID()
                        val filename = "${username.emailToFileName()}_photo_${uuid}.jpg"
                        Timber.w("testing saving $filename from uri $uri")
                        context.getRealPathFromURI(uri, filename)?.let {
                            add(it)
                        }
                    } else {
                        add(STRING_EMPTY)
                    }
                }
            }
        }
        Timber.w("testing setUserPhotos complete")
        return result
    }

    suspend fun setUserPhoto(username: String, source: String): String {
        val result = withContext(Dispatchers.IO) {
            if (source.isNotEmpty()) {
                val uri = Uri.parse(source)
                val uuid = UUID.randomUUID()
                val filename = "${username.emailToFileName()}_photo_${uuid}"
                Timber.w("testing saving $filename from uri $uri")
                context.getRealPathFromURI(uri, filename) ?: STRING_EMPTY
            } else {
                STRING_EMPTY
            }

        }
        Timber.w("testing setUserPhotos complete")
        return result
    }

    suspend fun getAllFiles(): List<String> {
        return context.loadPhotosUrisFromInternalStorage().map { it.toString() }
    }

    fun deleteFile(filename: String) {
        val deleteResult = context.deletePhotoFromInternalStorage(filename)
        Timber.w("testing deleteFile filename:$filename result-$deleteResult")
    }
}
