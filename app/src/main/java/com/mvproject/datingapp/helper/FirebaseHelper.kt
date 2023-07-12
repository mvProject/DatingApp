/*
 * Create by Medvediev Viktor
 * last update: 14.06.23, 10:30
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.helper

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.mvproject.datingapp.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseHelper @Inject constructor(
    @ApplicationContext context: Context
) {
    val currentUser
        get() = auth.currentUser
    val isUserAuthorized
        get() = currentUser != null

    private val auth = Firebase.auth
    private val database = FirebaseDatabase.getInstance().reference
    private val storage = FirebaseStorage.getInstance().reference

    suspend fun signUpUserWithCredential(user: User, images: List<String>): User? {
        val userCreated =
            auth.createUserWithEmailAndPassword(user.email, user.password).await().user
        return if (userCreated != null) {
            val userDb = user.copy(uid = userCreated.uid)
            saveUserToFirebase(userDb, images)
            getUserFromFirebase()
        } else {
            Timber.e("testing signUpUserWithCredential failure")
            null
        }
    }

    private fun saveUserToFirebase(
        user: User,
        images: List<String> = emptyList()
    ) {
        Timber.w("testing saveUserToFirebase user:$user")
        database.child("user").child(user.uid).setValue(user)
        if (images.isNotEmpty()) {
            saveUserImages(user.uid, images)
        }
    }

    suspend fun getUserFromFirebase(): User? {
        val uid = currentUser?.uid
        return if (uid != null) {
            val dataSnapshot = database.child("user").child(uid).get().await()
            val user = dataSnapshot.getValue<User>()
            Timber.w("testing getUserFromFirebase: $user")
            user
        } else null
    }

    private fun saveUserImages(userId: String, images: List<String>) {
        val imgRef = storage.child("userImages").child(userId)
        Timber.w("testing saveUserImages userId:$userId, images:$images")
        images.forEachIndexed { ind, uri ->
            Timber.w("testing saveUserImages ind:$ind uri:$uri")
            imgRef.child("image_$ind").putFile(Uri.parse(uri))
                .addOnSuccessListener {
                    Timber.w("testing img put success: ")
                }
                .addOnFailureListener {
                    Timber.e("testing img put error : $it")
                }
        }
    }

    suspend fun signInUserWithCredential(email: String, password: String): User? {
        val user = auth.signInWithEmailAndPassword(email, password).await().user
        return if (user != null) {
            getUserFromFirebase()
        } else {
            Timber.e("testing signInUserWithCredential failure")
            null
        }
    }

    fun verifyEmail() {
        val user = auth.currentUser
        user?.let { usr ->
            Timber.w("testing createUserWithEmailAndPassword:user:${usr.email}, isEmailVerified:${usr.isEmailVerified}")
            usr.sendEmailVerification()
                .addOnCompleteListener {
                    Timber.w("testing sendEmailVerification:task success")
                }.addOnFailureListener {
                    Timber.w("testing sendEmailVerification:task no success")
                }
        }
    }


}