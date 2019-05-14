package com.example.flickrpublicfeed.photoactivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrpublicfeed.R
import com.example.flickrpublicfeed.feed.logic.FeedItem
import com.example.flickrpublicfeed.photoactivity.RelevantPhotosAdapter
import com.example.flickrpublicfeed.photoactivity.fragments.SplitFragment.Companion.PHOTO_ITEM
import com.example.flickrpublicfeed.photoactivity.fragments.SplitFragment.Companion.RELEVANT_PHOTOS_URLS

class RelevantPhotosFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.relevant_photos_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val relevantPhotosUrls: ArrayList<String> = arguments?.getStringArrayList(RELEVANT_PHOTOS_URLS)!!

        view.findViewById<RecyclerView>(R.id.relevantPhotos).apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = RelevantPhotosAdapter(relevantPhotosUrls)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(item: FeedItem, relevantPhotosUrls: ArrayList<String>) =
            RelevantPhotosFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PHOTO_ITEM, item)
                    putStringArrayList(RELEVANT_PHOTOS_URLS, relevantPhotosUrls)
                }
            }
    }
}