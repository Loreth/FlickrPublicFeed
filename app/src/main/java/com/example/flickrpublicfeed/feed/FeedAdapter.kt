package com.example.flickrpublicfeed.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrpublicfeed.feed.logic.FeedItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.feed_item.view.*

class FeedAdapter(private val entries: MutableList<FeedItem>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(com.example.flickrpublicfeed.R.layout.feed_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]

        holder.imgName.text = entry.imgName
        holder.tags.text = entry.tags.joinToString()
        holder.date.text = entry.date

        val picasso = Picasso.get()
        picasso.isLoggingEnabled = true

        picasso
            .load(entry.imgUrl)
            .placeholder(com.example.flickrpublicfeed.R.drawable.kitty_sunglasses)
            .into(holder.image)
    }

    fun deleteItem(position: Int) {
        entries.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.itemImageView
        val imgName = itemView.itemImgName
        val tags = itemView.itemTags
        val date = itemView.itemDate
    }
}