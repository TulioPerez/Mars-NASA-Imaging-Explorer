package com.tulioperez

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class TestsValidated {

    //MainActivity:
    @Test
    fun `verifyCorrectNumberOfItemsDisplayed`(){
        val collection = mockk<JsonData.Collection>()
        val jsonData = JsonData(collection)
        val context = Mockito.mock(Context::class.java) // mocked context
        val adapter = Adapter(context, jsonData)

        val expectedItemCount = 5
        every { collection.items.size } returns expectedItemCount
        val actualItemCount = adapter.getItemCount()

        Assert.assertEquals(expectedItemCount, actualItemCount)
    }

    //ViewHolderComponents
    @Test
    fun testViewHolderComponents() {
        val imageView = mockk<ImageView>()
        val titleTextView = mockk<TextView>()
        val descTextView = mockk<TextView>()

        val data = mockk<JsonData>()

        val itemView = mockk<View> {
            every { findViewById<ImageView>(R.id.item_image) } returns imageView
            every { findViewById<TextView>(R.id.item_title) } returns titleTextView
            every { findViewById<TextView>(R.id.item_desc) } returns descTextView
        }

        val viewHolder = ViewHolder(itemView, data)

        val title = "Test Title"
        every { titleTextView.text } returns title

        val desc = "desc"
        every { descTextView.getText() } returns desc

        val drawable = mockk<Drawable>()
        every { imageView.getDrawable() } returns drawable

        TestCase.assertEquals(title, viewHolder.getTitle())
        TestCase.assertEquals(desc, viewHolder.getDesc())
        TestCase.assertEquals(drawable, viewHolder.getImage())
    }

}