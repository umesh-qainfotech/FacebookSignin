package com.example.facebooksigninfirebase

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.login.LoginManager

class Logout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        if (AccessToken.getCurrentAccessToken() == null)
        {
            goLoginScreen()
        }
    }

    private fun goLoginScreen(){
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

   public fun logout(view: View) {
       LoginManager.getInstance().logOut()
       goLoginScreen()
   }
}
