package com.example.facebooksigninfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var callbackManager : CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //AppEventsLogger.activateApp(getApplication())
        callbackManager = CallbackManager.Factory.create()
        fbLogin_button.setOnClickListener(View.OnClickListener {

            LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("email","public_profile"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult?> {
                    override fun onSuccess(loginResult: LoginResult?) {
                        Log.i("MainActivity","Facebook Login success")
                        Toast.makeText(this@MainActivity,"Success",Toast.LENGTH_LONG).show()
                        goMainScreen()
                    }

                    override fun onCancel() {
                        Log.i("MainActivity","onCancel")
                        Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
                    }

                    override fun onError(exception: FacebookException) {
                        Log.i("MainActivity","onError")
                        Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
                    }
                })
            })
        }

    private fun goMainScreen() {
        val intent = Intent(this, Logout::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    }
