package com.lemondropsarl.luka.ui.main.post

import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.di.Injectable
import com.lemondropsarl.luka.ui.base.fragment.BaseFragment
import com.lemondropsarl.luka.utils.OnRecyclerClickListener
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

        val mQuery = model.getQuery(current!!.uid)

        val options = FirestoreRecyclerOptions.Builder<Post>()
            .setLifecycleOwner(this)
            .setQuery(mQuery, Post::class.java)
            .build()
        val adapter = PostListAdapter(options)

        post_list.setHasFixedSize(true)
        post_list.adapter = adapter
        /*if (post_list.adapter!!.itemCount == 0){
            post_list.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        }else{
            post_list.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }*/
        adapter.setOnItemRecyclerClick(object : OnRecyclerClickListener{
            override fun onItemClick(position: Int) {
                val ds = adapter.getSnapshot(position)
                val postId = ds!!.id
                val action = PostListFragmentDirections.detail(postId)
                navController.navigate(action)
            }
        })
        enableSwipe(adapter)
    }
    private fun enableSwipe(adapter:PostListAdapter){
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT){
                    adapter.deleteItem(position)
                    //implement snackBar
                }
            }

        }
        ItemTouchHelper(simpleCallback).attachToRecyclerView(post_list)
    }
}