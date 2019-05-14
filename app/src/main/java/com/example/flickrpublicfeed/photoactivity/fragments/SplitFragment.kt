package com.example.flickrpublicfeed.photoactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flickrpublicfeed.R
import com.example.flickrpublicfeed.feed.logic.FeedItem


class SplitFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.split_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item: FeedItem = arguments?.getParcelable(PHOTO_ITEM)!!
        val relevantPhotosUrls: ArrayList<String> = arguments?.getStringArrayList(RELEVANT_PHOTOS_URLS)!!

        childFragmentManager.beginTransaction().apply {
            replace(R.id.detailsSplitFragment, DetailsFragment.newInstance(item))
            replace(R.id.relevantPhotosSplitFragment, RelevantPhotosFragment.newInstance(item, relevantPhotosUrls))
            commit()
        }
    }

    companion object {
        const val PHOTO_ITEM = "PHOTO_ITEM"
        const val RELEVANT_PHOTOS_URLS = "RELEVANT_PHOTOS_URLS"

        @JvmStatic
        fun newInstance(item: FeedItem, relevantPhotosUrls: ArrayList<String>) =
            SplitFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PHOTO_ITEM, item)
                    putStringArrayList(RELEVANT_PHOTOS_URLS, relevantPhotosUrls)
                }
            }
    }
}