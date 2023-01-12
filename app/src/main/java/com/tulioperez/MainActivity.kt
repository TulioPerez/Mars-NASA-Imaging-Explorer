package com.tulioperez

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

// Constants
const val BASE_URL = "https://images-api.nasa.gov"
const val API_KEY = "api_key=4uoKLb63ESN9e6c30hcgZU0hVPWQrkQaqI10b4u1"
const val URL = "$BASE_URL?$API_KEY/"


class MainActivity : AppCompatActivity() {

    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeView: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide status bar
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        window.decorView.setOnApplyWindowInsetsListener { view, windowInsets ->
            windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
            view.onApplyWindowInsets(windowInsets)
        }

        // Set fixed size for recyclerView to improve performance
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        // Set swipeView listener to re-fetch data if network or server errors occur
        swipeView = findViewById(R.id.swipe_view)
        swipeView.setOnRefreshListener {
            fetchData()
            swipeView.isRefreshing = false
        }

        fetchData()
    }

    // Populate views
    private fun fetchData() {
        val loader = findViewById<ProgressBar>(R.id.progress_loader)
        val errorView: ImageView = findViewById(R.id.error_image)

        // Are we running after a refresh?
        if (errorView.visibility == View.VISIBLE) {

            // Yes - get the views out of the way!
            errorView.visibility = View.GONE
            swipeView.visibility = View.GONE
        }

        // Show progress loader
        loader.visibility = View.VISIBLE

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitData = retrofitBuilder.create(ApiInterface::class.java).getData()

        retrofitData.enqueue(object : Callback<JsonData> {
            override fun onResponse(
                call: Call<JsonData>,
                response: Response<JsonData>
            ) {
                response.body()?.apply {
                    adapter = Adapter(baseContext, this)
                    recyclerView.adapter = adapter
                }

                // Done loading - remove the loader
                loader.visibility = View.GONE
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                // We had an error fetching data - present error imageView + swipeView to re-fetch
                errorView.visibility = View.VISIBLE
                swipeView.visibility = View.VISIBLE

                if (t is IOException) {
                    if (isNetworkConnected()) {
                        // Server error occurred
                        Toast.makeText(
                            this@MainActivity,
                            R.string.error_server, Toast.LENGTH_LONG
                        ).show()

                        errorView.setImageResource(R.drawable.image_no_cloud)

                    } else {
                        // Network error occurred
                        Toast.makeText(
                            this@MainActivity,
                            R.string.error_connection, Toast.LENGTH_LONG
                        ).show()

                        errorView.setImageResource(R.drawable.image_no_signal)

                    }

                }
                loader.visibility = View.GONE
            }
        })

    }

    // User wants to leave? Prompt to press back again to exit the application
    private var backPressed = false
    override fun onBackPressed() {
        if (backPressed) {
            super.onBackPressed()
            return
        }
        backPressed = true
        Toast.makeText(this, R.string.exit_message, Toast.LENGTH_SHORT).show()
        recyclerView.postDelayed({ backPressed = false }, 2000)
    }

    // Helper function to check if there is an active internet connection
    private fun isNetworkConnected(): Boolean {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetwork
        return activeNetwork != null
    }

}
