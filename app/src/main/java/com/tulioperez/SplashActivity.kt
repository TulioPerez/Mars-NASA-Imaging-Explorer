package com.tulioperez

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

const val INTRO_DURATION: Long = 3000

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashLogo = findViewById<View>(R.id.splash_image)
        val splashLogoText = findViewById<View>(R.id.logo_text)
        val splashBlurb = findViewById<View>(R.id.logo_blurb)
        val versionField: TextView = findViewById(R.id.version_view)

        versionField.text = "Ver. " + BuildConfig.VERSION_NAME

        // Hide status bar and navigation bar
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        window.decorView.setOnApplyWindowInsetsListener { view, windowInsets ->
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            view.onApplyWindowInsets(windowInsets)
        }

        // Animate the BG image & text
        splashLogo.animate().alpha(1f).setDuration(INTRO_DURATION)
            .scaleX(1.5f)
            .scaleY(1.5f)

        splashLogoText.animate().alpha(1f).setDuration(2000)
        splashBlurb.animate().alpha(1f).setDuration(2000)
        versionField.animate().alpha(1f).setDuration(2000)

        splashLogo.animate().alpha(1f).setDuration(INTRO_DURATION)
            .withEndAction {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
    }
}
