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
import com.mvproject.datingapp.utils.emailToFileName
import com.mvproject.datingapp.utils.getRealPathFromURI
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun setUserPhotos(username: String, source: List<String>): List<String> {

        val uris = source.map {
            Uri.parse(it)
        }

        val result = buildList {
            withContext(Dispatchers.IO) {
                uris.forEachIndexed { index, uri ->
                    val filename = "${username.emailToFileName()}_photo_${index}"
                    Timber.w("testing saving $filename from uri $uri")
                    context.getRealPathFromURI(uri, filename)?.let {
                        add(it)
                    }

                }
            }
        }
        Timber.w("testing setUserPhotos complete")
        return result
    }
}
