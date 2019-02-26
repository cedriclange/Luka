package com.lemondropsarl.luka.ui.main.post

import android.os.Bundle
import android.view.View
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.di.Injectable
import com.lemondropsarl.luka.ui.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.post_list_fragment.*

class PostListFragment : BaseFragment<PostViewModel>(), Injectable {
    override val getLayoutViewRes: Int
        get() = R.layout.post_list_fragment
    override val viewModelClass: Class<PostViewModel>
        get() = PostViewModel::class.java

    lateinit var model: PostViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = getViewModel()
        initRecycler()
        add_post_btn.setOnClickListener {
            navController.navigate(PostListFragmentDirections.add())
        }

    }

    private fun initRecycler() {
        //setup recyclerView
        val current = FirebaseAuth.getInstance().currentUser

        val mQuery = model.getQuery(current?.uid!!)

        val options = FirestoreRecyclerOptions.Builder<Post>()
            .setLifecycleOwner(this)
            .setQuery(mQuery, Post::class.java)
            .build()
        val adapter = PostListAdapter(options)

        if (adapter.itemCount == 0){
            post_list.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        }else{
            post_list.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }

        post_list.hasFixedSize()
        post_list.adapter = adapter


    }
}