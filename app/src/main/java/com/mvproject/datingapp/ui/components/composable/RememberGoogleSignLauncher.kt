/*
 * Create by Medvediev Viktor
 * last update: 22.06.23, 11:26
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.composable

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignIn

@Composable
fun rememberGoogleSignLauncher(
    onAuthComplete: (String) -> Unit,
    onAuthError: (Int) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    val account = GoogleSignIn.getSignedInAccountFromIntent(intent).result
                    account?.idToken?.let { token ->
                        onAuthComplete(token)
                    }
                }
            } else {
                onAuthError(result.resultCode)
            }
        })
}