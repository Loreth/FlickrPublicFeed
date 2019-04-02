package com.example.flickrpublicfeed

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
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

        liveFeedRecyclerView.addItemDecoration(
            DividerItemDecoration(
                liveFeedRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        //hardcoded strings, as adding the first result is temporary, will be removed in version without the add feature
        entries.add(
            FeedItem(
                "https://www.koty.pl/wp-content/uploads/2017/09/cat-are-liquid-imgur-2.jpg",
                "Płynny kot", listOf("kot, płyn, naczynie"), "02.04.19"
            )
        )

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
            entries.add(data.getParcelableExtra(AddItemActivity.KEY_RESULT))
            feedAdapter.notifyDataSetChanged()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val FEED_ITEM_REQUEST_CODE = 0
    }
}
