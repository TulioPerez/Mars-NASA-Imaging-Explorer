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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title: TextView
        var desc: TextView
        var image: ImageView

        init {
            title = itemView.findViewById(R.id.item_title)
            desc = itemView.findViewById(R.id.item_desc)
            image = itemView.findViewById(R.id.item_image)
        }

        override fun onClick(view: View) {
            // create an intent to open the DetailActivity
            val intent = Intent(view.context, DetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // pass the adapter position
            intent.putExtra("position", adapterPosition)
            view.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        val holder = ViewHolder(itemView)
        itemView.setOnClickListener(holder)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = data.collection.items[position].data[0].title
        holder.desc.text = data.collection.items[position].data[0].description

        // Load & format images
        val href = data.collection.items[position].links[0].href

        Glide.with(context)
            .load(href)
            .thumbnail()
            .transition(withCrossFade())
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_no_signal)
            .transform(Rotate(90))
            .into(holder.image)

    }

    override fun getItemCount(): Int {
        return data.collection.items.size
    }


}