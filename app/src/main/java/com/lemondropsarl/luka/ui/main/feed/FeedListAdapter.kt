package com.lemondropsarl.luka.ui.main.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.utils.OnRecyclerClickListener


class FeedListAdapter(options: FirestorePagingOptions<Post>) : FirestorePagingAdapter<Post, FeedListHolder>(options) {

    lateinit var mlistener: OnRecyclerClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_item_row, parent, false)


        return FeedListHolder(view, mlistener)
    }

    override fun onBindViewHolder(holder: FeedListHolder, position: Int, data: Post) {


        holder.onBind(data)
    }


    fun getSnapShot(position: Int): DocumentSnapshot? {

        return getItem(position)
    }

    fun setOnItemRecyclerClick(listener: OnRecyclerClickListener) {
        this.mlistener = listener
    }


}
