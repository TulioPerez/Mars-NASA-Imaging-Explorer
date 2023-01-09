package com.tulioperez

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("search?keywords=mars")
    fun getData(): Call<JsonData>
}
