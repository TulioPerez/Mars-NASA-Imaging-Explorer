package com.tulioperez

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://images-api.nasa.gov"
const val API_KEY = "api_key=4uoKLb63ESN9e6c30hcgZU0hVPWQrkQaqI10b4u1"
const val URL = "$BASE_URL?$API_KEY/"


class MainActivity : AppCompatActivity() {

    lateinit var adapter: Adapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var data: JsonData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        getData()
    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<JsonData> {
            override fun onResponse(
                call: Call<JsonData>,
                response: Response<JsonData>) {
                data = response.body()!!

                adapter = Adapter(baseContext, data)
                adapter.notifyDataSetChanged()

                recyclerView.adapter = adapter

            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                d("MainActivity", "onFailure: ${t.message}")
            }
        })
    }

}
