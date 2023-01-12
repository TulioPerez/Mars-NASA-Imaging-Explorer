package com.tulioperez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jsibbold.zoomage.ZoomageView

class DetailActivity : AppCompatActivity() {

    private lateinit var titleView: TextView
    private lateinit var descView: TextView
    private lateinit var zoomView: ZoomageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        titleView = findViewById(R.id.detail_title)
        descView = findViewById(R.id.detail_desc)
        zoomView = findViewById(R.id.zoom_layout)
//        zoomView.autoCenter

        val intent = intent
        titleView.text = intent.getStringExtra("title")
        descView.text = intent.getStringExtra("description")

        val href = intent.getStringExtra("image")

        Glide.with(this)
            .load(href)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.image_no_wifi)
            .transform(Rotate(90))
//            .dontTransform()
            .into(zoomView)
    }

}
