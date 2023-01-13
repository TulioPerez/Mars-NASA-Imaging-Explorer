package com.tulioperez

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jsibbold.zoomage.ZoomageView
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class TestCases {
    val itemView = mockk<View>()
    val imageView = mockk<ImageView>()
    val titleTextView = mockk<TextView>()
    val descTextView = mockk<TextView>()
    val context = Mockito.mock(Context::class.java)
    val activity = mockk<DetailActivity>()
    val titleView = mockk<TextView>()
    val descView = mockk<TextView>()
    val zoomView = mockk<ZoomageView>()
    val drawable = mockk<Drawable>()
    val collection = mockk<JsonData.Collection>()
    val jsonData = JsonData(collection)
    val adapter = Adapter(context, jsonData)
    val expectedItemCount = 5

    val title = "Test Title"
    val desc = "Test Description"

    //ViewHolderComponents
    @Test
    fun testViewHolderComponents() {
        every { itemView.findViewById<TextView>(R.id.item_title) } returns titleTextView
        every { itemView.findViewById<TextView>(R.id.item_desc) } returns descTextView
        every { itemView.findViewById<ImageView>(R.id.item_image) } returns imageView

        every { titleTextView.text } returns title
        every { descTextView.text } returns desc
        every { imageView.drawable } returns drawable

        val viewHolder = ViewHolder(itemView, jsonData)

        TestCase.assertEquals(title, viewHolder.getTitle())
        TestCase.assertEquals(desc, viewHolder.getDesc())
        TestCase.assertEquals(drawable, viewHolder.getImage())
    }

    //MainActivity:
    @Test
    fun verifyItemsDisplayedEqualsItemsFetched() {
        every { collection.items.size } returns expectedItemCount
        val actualItemCount = adapter.getItemCount()

        Assert.assertEquals(expectedItemCount, actualItemCount)
    }

    //DetailActivity
    @Test
    fun testDetailActivityComponents() {
        every { activity.findViewById<TextView>(R.id.detail_title) } returns titleView
        every { titleView.text } returns title

        every { activity.findViewById<TextView>(R.id.detail_desc) } returns descView
        every { descView.text } returns desc

        every { activity.findViewById<ZoomageView>(R.id.zoom_layout) } returns zoomView
        every { zoomView.getDrawable() } returns drawable
    }

}