package com.tulioperez

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade


class Adapter(val context: Context, val data: JsonData) :
    RecyclerView.Adapter<ViewHolder>() {
    private val TAG = "Adapter"

    // Inflate & setup layout for RecyclerView items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        val holder = ViewHolder(itemView, data)

        itemView.setOnClickListener(holder)
        return holder
    }

    // Bind data to ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Prep placeholder (progress loader animation) for Glide images
        val loader = CircularProgressDrawable(context)
        loader.strokeWidth = 5f
        loader.centerRadius = 30f
        loader.start()

        holder.title.text = data.collection.items[position].data[0].title
        holder.desc.text = data.collection.items[position].data[0].description

        // Load & format images
        val href = data.collection.items[position].links[0].href

        Glide.with(context)
            .load(href)
            .transition(withCrossFade())
            .placeholder(loader)
            .error(R.drawable.image_no_wifi)
            .override(2000,2000)
            .transform(Rotate(-8))
            .into(holder.image)

        Log.d(TAG, "Image loaded: $href")

    }

    // Retrieve number of data items
    override fun getItemCount(): Int {
        return data.collection.items.size
    }

}