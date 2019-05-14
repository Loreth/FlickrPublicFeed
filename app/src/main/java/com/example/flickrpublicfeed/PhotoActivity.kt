package com.example.flickrpublicfeed

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.flickrpublicfeed.feed.logic.FeedItem
import com.example.flickrpublicfeed.photoactivity.PhotoPagerAdapter
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val photoEntry: FeedItem = intent.getParcelableExtra(ENTRY_KEY)
        val relevantPhotosUrls: ArrayList<String> = intent.getStringArrayListExtra(RELEVANT_PHOTOS_KEY)

        val viewPager: ViewPager = fragmentContainer
        viewPager.adapter = PhotoPagerAdapter(photoEntry, relevantPhotosUrls, supportFragmentManager)
    }

    companion object {
        const val ENTRY_KEY = "ENTRY"
        const val RELEVANT_PHOTOS_KEY = "RELEVANT_PHOTOS"
    }
}
