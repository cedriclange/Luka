package com.lemondropsarl.luka.ui.main.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.di.module.GlideApp
import com.lemondropsarl.luka.utils.OnRecyclerClickListener
import kotlinx.android.synthetic.main.post_item_row.view.*

class PostListAdapter(
    options: FirestoreRecyclerOptions<Post>

) :
    FirestoreRecyclerAdapter<Post, PostListAdapter.PostHoler>(options) {
    lateinit var listener : OnRecyclerClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHoler {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.post_item_row, parent, false)
        return PostHoler(view, listener)
    }

    override fun onBindViewHolder(holder: PostHoler, position: Int, post: Post) {
        holder.bind(post)

    }
    fun deleteItem(position:Int){
        getSnapshot(position)!!.reference.delete()

    }
    fun getSnapshot(position:Int) :DocumentSnapshot?{
        return getSnapshot(position)
    }
    fun setOnItemRecyclerClick(listener: OnRecyclerClickListener) {
        this.listener = listener
    }

    class PostHoler constructor(itemView: View
    , private val listener: OnRecyclerClickListener)

        : RecyclerView.ViewHolder(itemView) {
        companion object {
            const val NO_POSITION =-1
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != NO_POSITION){
                    listener.onItemClick(position)
                }
            }
        }

        fun bind(post: Post){

            itemView.postTitle.text = post.title
            GlideApp.with(itemView.postImage.context!!)
                .load(post.photoUrl)
                .into(itemView.postImage)
            val isPublished = post.isPublished

            if (!isPublished) {
                itemView.publish_btn.visibility = View.VISIBLE
                itemView.publishedImage.visibility = View.GONE
            } else {
                itemView.publish_btn.visibility = View.GONE
                itemView.publishedImage.visibility = View.VISIBLE

            }

        }

    }

}