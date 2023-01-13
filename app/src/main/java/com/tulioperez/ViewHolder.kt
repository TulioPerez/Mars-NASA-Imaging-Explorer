package com.tulioperez

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ViewHolder(itemView: View, val data: JsonData) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    private val TAG = "ViewHolder"

    // Initialize ViewHolder elements
    val title: TextView = itemView.findViewById(R.id.item_title)
    val desc: TextView = itemView.findViewById(R.id.item_desc)
    val image: ImageView = itemView.findViewById(R.id.item_image)

    // User tapped an itemView - open DetailActivity
    override fun onClick(view: View) {
        val intent = Intent(view.context, DetailActivity::class.java)

        // If high res image is available, send its href instead of the thumbnail
        var href = data.collection.items[adapterPosition].links[0].href
        href = modifyHref(href)

        // Pass extras to detail activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("title", title.text)
        intent.putExtra("description", desc.text)
        intent.putExtra("image", href)

        view.context.startActivity(intent)
    }

    //Helper function checks if image retrieved is low-res and replaces it if so
    fun modifyHref(href: String): String {
        val regex = Regex("(thumb|medium|small)")
        Log.d(TAG, "href: $href replaced with high res version")
        return href.replaceFirst(regex,"orig")
    }
}