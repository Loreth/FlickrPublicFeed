package com.example.flickrpublicfeed.photoactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrpublicfeed.R
import com.squareup.picasso.Picasso

class RelevantPhotosAdapter(val relevantPhotosUrls: List<String>): RecyclerView.Adapter<RelevantPhotosAdapter.RelevantPhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelevantPhotosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_relevant_photo, parent, false)

        return RelevantPhotosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return relevantPhotosUrls.size
    }

    override fun onBindViewHolder(holder: RelevantPhotosViewHolder, position: Int) {
        holder.bind(relevantPhotosUrls[position])
    }

    class RelevantPhotosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.findViewById(R.id.relevantPhoto)

        fun bind(url: String) {
            Picasso.get()
                .load(url)
                .error(R.drawable.kitty_sunglasses)
                .into(photo)
        }
    }
}