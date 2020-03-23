package com.ybbae.diaryofservant.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.ui.core.setContent
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.ybbae.diaryofservant.R
import com.ybbae.diaryofservant.model.user.SessionManager
import com.ybbae.diaryofservant.ui.login.SignInActivity
import com.ybbae.diaryofservant.ui.main.AppMain

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        SessionManager.init(this)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())
//            AuthUI.IdpConfig.FacebookBuilder().build(),
//            AuthUI.IdpConfig.TwitterBuilder().build())

        val authMethodPickerLayout = AuthMethodPickerLayout.Builder(R.layout.auth_method_picker)
            .setGoogleButtonId(R.id.btn_signin_google)
            .setEmailButtonId(R.id.btn_signin_email)
            .setPhoneButtonId(R.id.btn_signin_phone)
            .build()

        setContent {
            AppMain() {
                startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setAuthMethodPickerLayout(authMethodPickerLayout)
                    .build(),
                    RC_SIGN_IN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                Log.d(TAG, FirebaseAuth.getInstance().currentUser.toString())
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                Log.e(TAG, "Error sign in : " + response?.error?.errorCode)
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 123
        private const val TAG = "MainActivity"
    }
}