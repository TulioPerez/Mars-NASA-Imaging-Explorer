package com.tulioperez

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jsibbold.zoomage.ZoomageView
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Test
import org.mockito.Mockito.verify

class DetailActivityTests {
    @Test
    fun testDetailActivityOnCreate() {
        val titleView = mockk<TextView>()
        val descView = mockk<TextView>()
        val zoomView = mockk<ZoomageView>()
        val glide = mockk<Glide>()
        val bundle = mockk<Bundle>()
        val intent = mockk<Intent>()

        val activity = spyk(DetailActivity())
        every { activity.findViewById<TextView>(R.id.detail_title) } returns titleView
        every { activity.findViewById<TextView>(R.id.detail_desc) } returns descView
        every { activity.findViewById<ZoomageView>(R.id.zoom_layout) } returns zoomView

        every { activity.intent } returns intent
        every { intent.getStringExtra("title") } returns "Test Title"
        every { intent.getStringExtra("description") } returns "Test Description"
        every { intent.getStringExtra("image") } returns "image_url"

        every { titleView.text = "Test Title" } returns Unit
        every { descView.text = "Test Description" } returns Unit

//        every { Glide.with(activity) } returns glide
//        every { glide.load("image_url") } returns glide
//        every { glide.transition(DrawableTransitionOptions.withCrossFade()) } returns glide
//        every { glide.error(R.drawable.image_no_wifi) } returns glide
//        every { glide.transform(Rotate(90)) } returns glide
//        every { glide.into(zoomView) } returns Unit
//
//        activity.onCreate(bundle)

        verify { activity.setContentView(R.layout.activity_detail) }
        verify { titleView.text = "Test Title" }
        verify { descView.text = "Test Description" }

        verify { Glide.with(activity) }
//        verify { glide.load("image_url") }
//        verify { glide.transition(DrawableTransitionOptions.withCrossFade()) }
//        verify { glide.error(R.drawable.image_no_wifi) }
//        verify { glide.transform(Rotate(90)) }
//        verify { glide.into(zoomView) }

    }
}