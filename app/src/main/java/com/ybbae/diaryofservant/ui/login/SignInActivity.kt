package com.ybbae.diaryofservant.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.ybbae.diaryofservant.model.user.SessionManager

class SignInActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContent {
			SigninScreen() {
				signIn()
			}
		}

		SessionManager.init(this)
	}

	private fun signIn() {
		val signInIntent = SessionManager.getSignInIntent()
		startActivityForResult(signInIntent, RC_SIGN_IN)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
	{
		super.onActivityResult(requestCode, resultCode, data)

		if (requestCode == RC_SIGN_IN) {
			val task = GoogleSignIn.getSignedInAccountFromIntent(data)
			try
			{
				val account = task.getResult(ApiException::class.java)
				SessionManager.firebaseAuthWithGoogle(account!!)

				finish()
			}
			catch (e: ApiException) {
				Log.w(TAG, "Google sign in failed", e)
			}
		}
	}

	companion object {
		private const val TAG = "SignInActivity"
		private const val RC_SIGN_IN = 9001
	}
}