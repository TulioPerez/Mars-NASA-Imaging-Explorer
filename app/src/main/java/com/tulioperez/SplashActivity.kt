package com.tulioperez

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val INTRO_DURATION: Long = 3000

class SplashActivity : AppCompatActivity() {
    private lateinit var versionNum: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashLogo = findViewById<View>(R.id.splash_image)
        val splashLogoText = findViewById<View>(R.id.logo_text)
        val splashBlurb = findViewById<View>(R.id.logo_blurb)
        val versionField: TextView = findViewById(R.id.version_view)

        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            versionNum = "Ver. "
            versionNum += packageInfo.versionName
            versionField.text = versionNum
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        //Remove Cutout & Bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        splashLogo.animate().alpha(1f)
            .setDuration(INTRO_DURATION)
            .scaleX(2.5f)
            .scaleY(2.5f)

        splashLogoText.animate().alpha(1f).setDuration(2000)
        splashBlurb.animate().alpha(1f).setDuration(2000)
        versionField.animate().alpha(1f).setDuration(2000)

        splashLogo.animate().alpha(1f)
            .setDuration(INTRO_DURATION)
            .withEndAction {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
//            overridePendingTransition(com.bumptech.glide.R.anim.abc_fade_in, 0)
//            overridePendingTransition(R.anim.fade_in, 0)
            }

    }
}

//TODO implement splash screen
// implement circular progressbar wheel
// implement tests
// do documentation
// Mars BG image
// Mars color scheme
// add color filter to images
// fix transition from splash to main