package com.example.flickrpublicfeed.photoactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.flickrpublicfeed.R
import com.example.flickrpublicfeed.feed.logic.FeedItem
import com.example.flickrpublicfeed.photoactivity.fragments.SplitFragment.Companion.PHOTO_ITEM
import com.squareup.picasso.Picasso

class PhotoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.photo_fragment, container, false)

        val item: FeedItem = arguments?.getParcelable(PHOTO_ITEM)!!
        val imageView: ImageView = view.findViewById(R.id.singlePhotoImageView)
        Picasso.get()
            .load(item.imgUrl)
            .into(imageView)

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(item: FeedItem) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PHOTO_ITEM, item)
                }
            }
    }
}