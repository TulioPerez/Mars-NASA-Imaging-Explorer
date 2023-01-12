package com.tulioperez

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock

class MainActivityTest {
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





}