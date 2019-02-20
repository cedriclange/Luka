package com.lemondropsarl.luka.ui.main.feed

import android.os.Bundle
import androidx.lifecycle.Observer
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.di.Injectable
import com.lemondropsarl.luka.di.module.GlideApp
import com.lemondropsarl.luka.ui.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.feed_detail_fragment.*

class FeedDetailFragment : BaseFragment<FeedViewModel>(), Injectable {
    override val getLayoutViewRes: Int
        get() = R.layout.feed_detail_fragment
    override val viewModelClass: Class<FeedViewModel>
        get() = FeedViewModel::class.java
    lateinit var model: FeedViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = FeedDetailFragmentArgs.fromBundle(arguments!!)
        val feedId = safeArgs.feedId
        //get viewModel
        model = getViewModel()
        updateUI(feedId)

    }


    private fun updateUI(id: String) {

        model.getFeed(id).observe(this, Observer { feed ->

            // update UI
            feedTitle.text = feed.title
            feedTown.text = feed.city
            feedArea.text = feed.area
            feedAuthorText.text = feed.author
            feedPrice.text = feed.price.toString()
            feedGuaranty.text = feed.guaranty.toString()
            feedRoom.text = feed.rooms.toString()
            GlideApp.with(feedImageView.context)
                .load(feed.photoUrl)
                .into(feedImageView)

        })

// las to implement is to add to favorite on Click
    }


}