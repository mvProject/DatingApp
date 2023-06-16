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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.mvproject.datingapp.data.User
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class FirebaseHelper @Inject constructor(
    @ApplicationContext context: Context
) {
    private val optionsFirebase = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        //.requestIdToken(webClientId)
        .requestEmail()
        .build()

    val googleSignClientFirebase: GoogleSignInClient =
        GoogleSignIn.getClient(context, optionsFirebase)

    val currentUser
        get() = auth.currentUser

    private val auth = Firebase.auth

    private val database = FirebaseDatabase.getInstance().reference
    private val storage = FirebaseStorage.getInstance().reference

    fun handleGoogleAccessToken(googleToken: String, onUserUpdate: (FirebaseUser?) -> Unit) {
        val credentials = GoogleAuthProvider.getCredential(googleToken, null)
        auth.signInWithCredential(credentials).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Timber.w("testing handleGoogleAccessToken:success")
                val user = auth.currentUser
                user?.let { usr ->
                    if (usr.displayName != null && usr.email != null) {
                        val userDb = User(
                            name = usr.displayName!!,
                            email = usr.email!!,
                            uid = usr.uid
                        )

                        addUserToDatabase(userDb)
                    }
                }
                onUserUpdate(user)
            } else {
                // If sign in fails, display a message to the user.
                Timber.e("testing handleGoogleAccessToken:failure ${task.exception}")
                onUserUpdate(null)
            }
        }
    }

    fun signUpUser(user: User, images: List<String>) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { task ->
                Timber.w("testing createUserWithEmailAndPassword:task ${task.user?.email}")
                task.user?.let { usr ->
                    val userDb = user.copy(uid = usr.uid)
                    addUserToDatabase(userDb)
                    saveUserImages(usr.uid, images)
                }

            }.addOnFailureListener { exception ->
                Timber.e("testing createUserWithEmailAndPassword:failure $exception")
            }
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

    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { task ->
                task.user?.let { usr ->
                    database.child("user").child(usr.uid).get()
                        .addOnSuccessListener { dataSnapshot ->
                            val user = dataSnapshot.getValue<User>()
                            Timber.w("testing signInUser:user $user")
                        }

                }

            }.addOnFailureListener { exception ->
                Timber.e("testing signInUser:failure $exception")
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

    fun signOutFirebase(onSuccess: () -> Unit) {
        googleSignClientFirebase.signOut()
            .addOnSuccessListener {
                Timber.e("testing signOut OnSuccess")
                auth.signOut()
                onSuccess()
            }
            .addOnCanceledListener {
                Timber.e("testing signOut OnCanceled")
            }
            .addOnFailureListener {
                Timber.e("testing signOut OnFailure ${it.localizedMessage}")
            }
    }

    private fun addUserToDatabase(user: User) {
        Timber.w("testing addUserToDatabase user:$user")
        database.child("user").child(user.uid).setValue(user)
    }

    private companion object {
        const val webClientId =
            "644171526644-a4pq9aru9gh752sdt4fmr2m0l0b2q487.apps.googleusercontent.com"
    }
}