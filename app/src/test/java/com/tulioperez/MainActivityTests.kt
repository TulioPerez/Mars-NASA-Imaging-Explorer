package com.tulioperez

import android.content.Context
import android.view.View
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock


class MainActivityTests {
    @Test
    fun `verifyCorrectNumberOfItemsDisplayed`(){
        val collection = mockk<com.tulioperez.JsonData.Collection>()
        val jsonData = JsonData(collection)
        val context = mock(Context::class.java) // mocked context
        val adapter = Adapter(context, jsonData)

        val expectedItemCount = 5
        every { collection.items.size } returns expectedItemCount
        val actualItemCount = adapter.getItemCount()

        assertEquals(expectedItemCount, actualItemCount)
    }

    @Test
    fun verifyfetchdata() {
        val activity = MainActivity()
        val result = activity.fetchData()
        assertEquals(result, true)
    }

    @Test
    fun testViewHolderTitle() {
        val itemView = mockk<View>()
        val data = mockk<JsonData>()
        val viewHolder = ViewHolder(itemView, data)

        viewHolder.title.text = "Test"

        TestCase.assertEquals("Test", viewHolder.getTitle())
    }


//    @Test
//    fun verifyOnBindViewHolderBindsCorrectData(){
//        val data = mockk<JsonData.Collection>()
//        val context = mockk<Context>()
//        val adapter = Adapter(context, JsonData(data))
//        val viewHolder = mockk<Adapter.ViewHolder>()
//        val position = 0
//        adapter.onBindViewHolder(viewHolder, position)
//
//        val expectedTitle = "title"
//        val expectedDescription = "description"
//        every { data.items[position].data[0].title } returns expectedTitle
//        every { data.items[position].data[0].description } returns expectedDescription
//        every { viewHolder.getTitle() } returns expectedTitle
//        every { viewHolder.getDesc() } returns expectedDescription
//
//        assertEquals(expectedTitle, viewHolder.getTitle())
//        assertEquals(expectedDescription, viewHolder.getDesc())
//    }


}