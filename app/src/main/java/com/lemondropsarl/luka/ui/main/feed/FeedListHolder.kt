package com.lemondropsarl.luka.ui.main.feed

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.di.module.GlideApp
import kotlinx.android.synthetic.main.feed_item_row.view.*

class FeedListHolder constructor(
    itemView: View, private val listener: FeedListAdapter.OnItemClickListener

) : RecyclerView.ViewHolder(itemView) {
    companion object {
        const val NO_POSITION = -1
    }

    init {
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != NO_POSITION) {
                listener.onItemClick(position)
            }

        }

    }


    fun onBind(post: Post) {
        //bind data to the list
        itemView.feedTitle.text = post.title
        itemView.textForfeedPrice.text = post.price.toString()

        GlideApp.with(itemView.feedImage.context)
            .load(post.postUrl)
            .into(itemView.feedImage)


    }
}
