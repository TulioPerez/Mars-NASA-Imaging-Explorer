package com.tulioperez

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test


class ViewHolderTests {

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

        assertEquals(title, viewHolder.getTitle())
        assertEquals(desc, viewHolder.getDesc())
        assertEquals(drawable, viewHolder.getImage())
    }

}