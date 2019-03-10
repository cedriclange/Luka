package com.lemondropsarl.luka.ui.main.feed

import android.os.Bundle
import androidx.paging.PagedList
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.di.Injectable
import com.lemondropsarl.luka.ui.base.fragment.BaseFragment
import com.lemondropsarl.luka.utils.OnRecyclerClickListener
import kotlinx.android.synthetic.main.feed_list_fragment.*

class FeedListFragment : BaseFragment<FeedViewModel>(), Injectable {
    override val getLayoutViewRes: Int
        get() = R.layout.feed_list_fragment
    override val viewModelClass: Class<FeedViewModel>
        get() = FeedViewModel::class.java


    //@Inject
    //lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var model: FeedViewModel
    //private val navController: NavController by lazy { findNavController() }
    //private val navController: NavController by lazy { findNavController() }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //get view model

        //model = ViewModelProviders.of(this, viewModelFactory)
        //.get(FeedViewModel::class.java)
        model = getViewModel()
        initFeedRecycler()
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
        adapter.setOnItemRecyclerClick(object : OnRecyclerClickListener {
            override fun onItemClick(position: Int) {
                val ds = adapter.getSnapShot(position)
                val id = ds!!.id
                //apply navigation
                val action = FeedListFragmentDirections.detail(id)
                navController.navigate(action)


            }
        })


    }
}


