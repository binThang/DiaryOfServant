package com.ybbae.diaryofservant.model.user

import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.compose.Model
import androidx.ui.material.Snackbar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.ybbae.diaryofservant.R

object SessionManager
{
	private lateinit var mAuth: FirebaseAuth
	private lateinit var mGoogleSignInClient: GoogleSignInClient
	private var mUser: FirebaseUser? = null

	fun init(context:Context) {
		// Configure Google Sign In
		val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestIdToken(context.getString(R.string.ybbdefault_web_client_id))
			.requestEmail()
			.build()

		mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
		mAuth = FirebaseAuth.getInstance()
		mUser = mAuth.currentUser
	}

	fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
		Log.d("SignIn", "firebaseAuthWithGoogle:" + acct.id)

		val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
		mAuth.signInWithCredential(credential)
			.addOnCompleteListener { task ->
				if (task.isSuccessful) {
					Log.d("SignIn", "signInWithCredential:success")
					val user = mAuth.currentUser
				} else {
				}
			}
	}

	fun getSignInIntent(): Intent
	{
		return mGoogleSignInClient.signInIntent;
	}

	fun getUser(): FirebaseUser?
	{
		return mAuth.currentUser
	}
}