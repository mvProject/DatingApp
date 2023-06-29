/*
 * Create by Medvediev Viktor
 * last update: 21.06.23, 17:46
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.helper

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mvproject.datingapp.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleSignHelper @Inject constructor(
    @ApplicationContext context: Context
) {
    private val webClientId =
        "644171526644-a4pq9aru9gh752sdt4fmr2m0l0b2q487.apps.googleusercontent.com"

    private val googleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()

    private val _googleSignClient = GoogleSignIn.getClient(context, googleSignInOptions)

    val googleSignClient get() = _googleSignClient

    suspend fun signInWithGoogle(token: String): User? {
        val credentials = GoogleAuthProvider.getCredential(token, null)
        val user = Firebase.auth.signInWithCredential(credentials).await().user
        return if (user != null) {
            val userDb = User(
                name = user.displayName,
                email = user.email!!,
                uid = user.uid,
            )
            //saveUserToFirebase(userDb)
            //getUserFromFirebase()
            userDb
        } else {
            Timber.e("testing signInWithGoogleAccessToken failure")
            null
        }
    }

    suspend fun signOutGoogleAccount() {
        return try {
            _googleSignClient.signOut().await()
            Timber.w("testing signOutGoogleNormal complete")
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.e("testing signOutGoogleNormal failure: ${e.localizedMessage} ")
        }
    }
}