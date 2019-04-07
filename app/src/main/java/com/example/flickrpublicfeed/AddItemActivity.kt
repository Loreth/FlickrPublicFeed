package com.example.flickrpublicfeed

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flickrpublicfeed.feed.logic.FeedItem
import kotlinx.android.synthetic.main.activity_add_item.*
import java.text.SimpleDateFormat
import java.util.*

class AddItemActivity : AppCompatActivity() {
    val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.title_activity_add_item)
        actionbar.setDisplayHomeAsUpEnabled(true)

        addItemButton.setOnClickListener {
            putDataInReturnIntent()
            finish()
        }


        val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        dateInput.setOnClickListener {
            DatePickerDialog(
                this@AddItemActivity,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateLabel() {
        val myFormat = DATA_FORMAT
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        dateInput.setText(sdf.format(myCalendar.time))
    }

    private fun putDataInReturnIntent() {
        if (urlInput.text.isNotBlank() &&
            imgNameInput.text.isNotBlank() &&
            dateInput.text.isNotBlank()
        ) {
            val intent = Intent()
            val feedItem = FeedItem(
                urlInput.text.toString(),
                imgNameInput.text.toString(),
                dateInput.text.toString()
            )
            intent.putExtra(KEY_RESULT, feedItem)
            setResult(RESULT_OK, intent)
        } else {
            setResult(RESULT_ERROR)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val RESULT_OK = 0
        const val RESULT_ERROR = 1
        const val KEY_RESULT = "newItem"
        const val DATA_FORMAT = "MM.dd.yyyy"
    }
}
