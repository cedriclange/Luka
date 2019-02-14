package com.lemondropsarl.luka.ui.main.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.di.Injectable
import kotlinx.android.synthetic.main.feed_list_fragment.*
import javax.inject.Inject

class FeedListFragment : Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var model: FeedViewModel
    //var adapter : FeedListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.feed_list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //get view model
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(FeedViewModel::class.java)

        initSwipeRefresh()
        initFeedRecycler()
    }

    private fun initSwipeRefresh() {

    }


    private fun initFeedRecycler() {
        //get paging auery
        var mQuery = model.basicQuery

        //configure  the pageList
        var config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(10)
            .setPageSize(20)
            .build()

        // implement Firestore Paginf Option

        val options = FirestorePagingOptions.Builder<Post>()
            .setLifecycleOwner(this)
            .setQuery(mQuery, config, Post::class.java)
            .build()

        val adapter = FeedListAdapter(options)

        feed_list.adapter = adapter
        adapter.setOnItemClickListener(object : FeedListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val ds = adapter.getSnapShot(position)
                val id: String = ds!!.id
                //apply navigation

            }
        })


    }
}


