package com.tulioperez

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade


class Adapter(val context: Context, val data: JsonData) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        // Initialize ViewHolder elements
        var title: TextView = itemView.findViewById(R.id.item_title)
        var desc: TextView = itemView.findViewById(R.id.item_desc)
        var image: ImageView = itemView.findViewById(R.id.item_image)

        // User tapped an itemView - open DetailActivity
        override fun onClick(view: View) {
            val intent = Intent(view.context, DetailActivity::class.java)

            // Pass extras to detail activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("title", title.text)
            intent.putExtra("description", desc.text)
            intent.putExtra("image", data.collection.items[adapterPosition].links[0].href)

            view.context.startActivity(intent)
        }
    }

    // Inflate & setup layout for RecyclerView items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        val holder = ViewHolder(itemView)

        itemView.setOnClickListener(holder)
        return holder
    }

    // Bind data to ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = data.collection.items[position].data[0].title
        holder.desc.text = data.collection.items[position].data[0].description

        // Load & format images
        val href = data.collection.items[position].links[0].href

        Glide.with(context)
            .load(href)
            .transition(withCrossFade())
            .error(R.drawable.image_no_wifi)
            .transform(Rotate(82))
            .into(holder.image)
    }

    // Retrieve number of data items
    override fun getItemCount(): Int {
        return data.collection.items.size
    }

}