package com.lemondropsarl.luka.ui.main.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState
import com.google.firebase.firestore.DocumentSnapshot
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.remote.model.Post


class FeedListAdapter(options: FirestorePagingOptions<Post>) : FirestorePagingAdapter<Post, FeedListHolder>(options) {

    lateinit var mlistener: FeedListAdapter.OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_item_row, parent, false)


        return FeedListHolder(view, mlistener)
    }

    override fun onBindViewHolder(holder: FeedListHolder, position: Int, data: Post) {
        //holder.mlistener.onItemClick(getItem(position), position)

        holder.onBind(data)
    }

    override fun onLoadingStateChanged(state: LoadingState) {
        when (state) {
            LoadingState.LOADING_INITIAL -> {
                //IMPLEMENT START LOADING
            }
            LoadingState.LOADING_MORE    -> {
                //IMPLEMENT LOADING MOREITEMS
            }
            LoadingState.LOADED          -> {
                //WHEN SUCCESSFULLY LOADED
            }
            LoadingState.ERROR           -> {
                //WHEN ERROR OCCUR
            }
        }

    }

    fun getSnapShot(position: Int): DocumentSnapshot? {
        return getItem(position)
    }

    fun setOnItemClickListener(listener: FeedListAdapter.OnItemClickListener) {
        this.mlistener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
