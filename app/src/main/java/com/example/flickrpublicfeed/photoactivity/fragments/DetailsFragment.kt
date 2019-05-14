package com.example.flickrpublicfeed.photoactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.flickrpublicfeed.R
import com.example.flickrpublicfeed.feed.FeedAdapter.Companion.TAGS_DELIMITER
import com.example.flickrpublicfeed.feed.logic.FeedItem

class DetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.details_fragment, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item: FeedItem = arguments?.getParcelable(PHOTO_ITEM)!!
        view.findViewById<TextView>(R.id.detailsImgName).text = item.imgName
        view.findViewById<TextView>(R.id.detailsDate).text = item.date
        view.findViewById<TextView>(R.id.detailsTags).text = item.tags.joinToString(TAGS_DELIMITER)
    }

    companion object {
        private const val PHOTO_ITEM = "PHOTO_ITEM"

        @JvmStatic
        fun newInstance(item: FeedItem) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PHOTO_ITEM, item)
                }
            }
    }
}