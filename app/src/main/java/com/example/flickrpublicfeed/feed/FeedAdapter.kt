package com.example.flickrpublicfeed.feed

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrpublicfeed.feed.logic.FeedItem
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
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
        val picasso = Picasso.get()

        holder.imgName.text = entry.imgName
        holder.date.text = entry.date

        picasso
            .load(entry.imgUrl)
            .placeholder(com.example.flickrpublicfeed.R.drawable.kitty_sunglasses)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    holder.image.setImageDrawable(placeHolderDrawable)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    Log.wtf("LAB", e)
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    processImageTagging(bitmap!!, holder)
                    holder.image.setImageBitmap(bitmap)
                }
            })
    }

    fun deleteItem(position: Int) {
        entries.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun processImageTagging(bitmap: Bitmap, holder: ViewHolder) {
        val visionImg = FirebaseVisionImage.fromBitmap(bitmap)
        val labeler = FirebaseVision.getInstance().onDeviceImageLabeler
        labeler.processImage(visionImg)
            .addOnSuccessListener { tagsProvided ->
                holder.tags.text = tagsProvided.map{ it.text }.take(3).joinToString(TAGS_DELIMITER)
            }
            .addOnFailureListener { ex ->
                Log.wtf("LAB", ex)
            }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.itemImageView
        val imgName = itemView.itemImgName
        val tags = itemView.itemTags
        val date = itemView.itemDate
    }

    companion object {
        const val TAGS_DELIMITER = ", "
    }
}