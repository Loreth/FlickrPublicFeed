package com.example.flickrpublicfeed.feed.logic

import android.os.Parcel
import android.os.Parcelable

data class FeedItem(val imgUrl: String, val imgName: String, val tags: List<String>, val date: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imgUrl)
        parcel.writeString(imgName)
        parcel.writeStringList(tags)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeedItem> {
        override fun createFromParcel(parcel: Parcel): FeedItem {
            return FeedItem(parcel)
        }

        override fun newArray(size: Int): Array<FeedItem?> {
            return arrayOfNulls(size)
        }
    }
}