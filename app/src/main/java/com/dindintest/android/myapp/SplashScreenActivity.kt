package com.dindintest.android.myapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

  companion object {
    const val SPLASH_SCREEN_DURATION = 2000L
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // make the activity full screen
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(R.layout.splash)

    supportActionBar?.hide()
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

    Handler().postDelayed({

      startActivity(Intent(this, MainActivity::class.java))
      overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
      finish()

    }, SPLASH_SCREEN_DURATION)
  }
}