package com.tulioperez

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View, val data: JsonData) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    // Initialize ViewHolder elements
    val title: TextView = itemView.findViewById(R.id.item_title)
    val desc: TextView = itemView.findViewById(R.id.item_desc)
    val image: ImageView = itemView.findViewById(R.id.item_image)

    // Getters for testing
    fun getTitle() = title.text
    fun getDesc() = desc.text
    fun getImage() = image.drawable

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