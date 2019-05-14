package com.example.flickrpublicfeed.photoactivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.flickrpublicfeed.feed.logic.FeedItem
import com.example.flickrpublicfeed.photoactivity.fragments.PhotoFragment
import com.example.flickrpublicfeed.photoactivity.fragments.SplitFragment

class PhotoPagerAdapter(
    private val photoEntry: FeedItem,
    private val relevantPhotosUrls: ArrayList<String>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PhotoFragment.newInstance(photoEntry)
            1 -> SplitFragment.newInstance(photoEntry, relevantPhotosUrls)
            else -> SplitFragment.newInstance(photoEntry, relevantPhotosUrls)
        }
    }

    override fun getCount(): Int {
        return 2
    }
}