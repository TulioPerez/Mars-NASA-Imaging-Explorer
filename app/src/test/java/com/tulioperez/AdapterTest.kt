package com.tulioperez

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*

import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(JsonData::class)
class AdapterTests {
    @Mock
    lateinit var context: Context

    @Mock
    var jsonData: JsonData = mock(JsonData::class.java)

    lateinit var adapter: Adapter

    @Before
    fun setUp() {
        PowerMockito.mockStatic(JsonData::class.java)
        jsonData = mock(JsonData::class.java)
        `when`(jsonData.collection.items.size).thenReturn(5)
        adapter = Adapter(context, jsonData)
    }

    @Test
    fun onCreateViewHolder_shouldReturnViewHolder() {
        // Arrange
        val parent = mock(ViewGroup::class.java)
        val viewType = 0

        // Act
        val result = adapter.onCreateViewHolder(parent, viewType)

        // Assert
        assertThat(result, instanceOf(Adapter.ViewHolder::class.java))
    }

    @Test
    fun onBindViewHolder_shouldSetTextViews() {
        // Arrange
        val holder = mock(Adapter.ViewHolder::class.java)
        val position = 0

        // Act
        adapter.onBindViewHolder(holder, position)

        // Assert
        verify(holder.title).text = jsonData.collection.items[position].data[0].title
        verify(holder.desc).text = jsonData.collection.items[position].data[0].description
    }

    @Test
    fun getItemCount_shouldReturnCorrectCount() {
        // Act
        val result = adapter.getItemCount()

        // Assert
        assertEquals(5, result)
    }

    @Test
    fun onClick_shouldStartDetailActivity() {
        // Arrange
        val view = mock(View::class.java)
        val viewHolder = adapter.ViewHolder(view)

        // Act
        viewHolder.onClick(view)

        // Assert
        verify(context).startActivity(any(Intent::class.java))
    }

}