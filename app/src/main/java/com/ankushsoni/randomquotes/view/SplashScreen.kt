package com.ankushsoni.randomquotes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.ankushsoni.randomquotes.MainActivity



class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({ //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(i)
            // close homethis activity
            finish()
        }, 3000)}
}



