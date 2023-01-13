package com.tulioperez

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jsibbold.zoomage.ZoomageView


class DetailActivity : AppCompatActivity() {
    private val TAG = "DetailActivity"

    private lateinit var titleView: TextView
    private lateinit var descView: TextView
    private lateinit var zoomView: ZoomageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        titleView = findViewById(R.id.detail_title)
        descView = findViewById(R.id.detail_desc)
        zoomView = findViewById(R.id.zoom_layout)

        val intent = intent
        titleView.text = intent.getStringExtra("title")
        descView.text = intent.getStringExtra("description")

        val href = intent.getStringExtra("image")

        val loader = CircularProgressDrawable(this)
        loader.strokeWidth = 5f
        loader.centerRadius = 50f
        loader.setColorSchemeColors(Color.WHITE)
        loader.start()

        Glide.with(this)
            .load(href)
            .placeholder(loader)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.image_no_wifi)
            .transform(Rotate(90))
            .into(zoomView)

        Log.d(TAG, "Image loaded: $href")
    }

}
