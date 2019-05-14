package com.example.flickrpublicfeed

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrpublicfeed.feed.FeedAdapter
import com.example.flickrpublicfeed.feed.SwipeToDeleteCallback
import com.example.flickrpublicfeed.feed.logic.FeedItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val entries: MutableList<FeedItem> = mutableListOf()
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        liveFeedRecyclerView.addItemDecoration(
//            DividerItemDecoration(
//                liveFeedRecyclerView.context,
//                DividerItemDecoration.VERTICAL
//            )
//        )

        initializeData()
        feedAdapter = FeedAdapter(entries)

        liveFeedRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = feedAdapter
        }
        ItemTouchHelper(SwipeToDeleteCallback(feedAdapter)).attachToRecyclerView(liveFeedRecyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.feed_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_item_menu_button -> {
            startActivityForResult(Intent(this@MainActivity, AddItemActivity::class.java), FEED_ITEM_REQUEST_CODE)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FEED_ITEM_REQUEST_CODE && resultCode == AddItemActivity.RESULT_OK && data != null) {
            entries.add(0, data.getParcelableExtra(AddItemActivity.KEY_RESULT))
            feedAdapter.notifyItemInserted(0)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initializeData() {
        entries.add(
            FeedItem("https://i.kym-cdn.com/entries/icons/" +
                    "original/000/027/475/Screen_Shot_2018-10-25_at_11.02.15_AM.png",
                "pika :o",
                "11.02.1234")
        )
        entries.add(
            FeedItem("https://www.gsmmaniak.pl/wp-content/uploads/gsmmaniak/2017/08/Android-o-google.jpg",
                "androidO",
                "11.07.2000")
        )
        entries.add(
            FeedItem("https://www.autoblog.com/img/research/styles/photos/performance.jpg",
                "car",
                "11.07.2012")
        )
        entries.add(
            FeedItem("http://http.cat/404", "no kitty", "19.05.2019")
        )
        entries.add(
            FeedItem("http://http.cat/200", "ok kitty", "11.05.2019")
        )
        entries.add(
            FeedItem("https://www.ikea.com/gb/en/images/products/" +
                    "jokkmokk-chair-antique-stain__0475400_pe615581_s4.jpg",
                "chair",
                "11.07.2012")
        )
        entries.add(
            FeedItem("http://http.cat/100", "ball kitty", "19.02.2019")
        )
        entries.add(
            FeedItem("http://http.cat/415", "dj kitty", "12.05.2019")
        )
        entries.add(
            FeedItem("https://i.kinja-img.com/gawker-media/image/upload/" +
                    "s--HqfzgkTd--/c_scale,f_auto,fl_progressive,q_80,w_800/wp2qinp6fu0d8guhex9v.jpg",
                "good doggo",
                "13.07.2019")
        )
    }

    companion object {
        const val FEED_ITEM_REQUEST_CODE = 0
    }
}
