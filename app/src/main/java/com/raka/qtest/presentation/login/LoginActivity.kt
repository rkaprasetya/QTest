package com.raka.qtest.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.raka.qtest.QTestApplication
import com.raka.qtest.R
import com.raka.qtest.di.component.DaggerLoginComponent
import com.raka.qtest.di.component.LoginComponent
import com.raka.qtest.di.module.LoginModule
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    @Inject
    lateinit var viewModel: LoginViewModel
    lateinit var component: LoginComponent
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupDagger()
        setupUi()
        configureGoogleSignIn()
        setupFacebookLogin()
    }

    private fun setupFacebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        btn_login_facebook.setPermissions(Arrays.asList("email","public_profile"))
        btn_login_facebook.registerCallback(callbackManager,object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                viewModel.onSignInSuccessfull()
            }

            override fun onCancel() {
                showToast(getString(R.string.google_signin_failed))
            }

            override fun onError(error: FacebookException?) {
                showToast(getString(R.string.google_signin_failed))
            }
        })
    }

    private fun setupUi() {
        btn_login_google.setOnClickListener(this)
        btn_login_signin.setOnClickListener(this)
    }

    private fun setupDagger() {
        component = DaggerLoginComponent.builder().loginModule(LoginModule(this))
            .applicationComponent((application as QTestApplication).getApplicationComponent())
            .build()
        component.inject(this)
    }

    private fun configureGoogleSignIn() {
        FirebaseApp.initializeApp(this)
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_login_google -> signIn()
            R.id.btn_login_signin -> viewModel.onSignInSuccessfull()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode,resultCode,data)
        when (requestCode) {
            RC_SIGN_IN -> {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account)
                } catch (e: ApiException) {
                    showToast(getString(R.string.google_signin_failed))
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                viewModel.onSignInSuccessfull()
            } else {
                showToast(getString(R.string.google_signin_failed))
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    companion object {
        const val RC_SIGN_IN = 1
    }
}
