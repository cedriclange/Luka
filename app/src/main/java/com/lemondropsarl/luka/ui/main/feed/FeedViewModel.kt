package com.lemondropsarl.luka.ui.main.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.data.repository.FeedRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val repo: FeedRepository
) : ViewModel() {

    val feed = MutableLiveData<Post>()

    val basicQuery: Query = repo.getPagingQuery()

    fun getFeed(id: String): LiveData<Post> {
        repo.getById(id)
            .addOnSuccessListener { document ->
                var post = document?.toObject(Post::class.java)
                feed.value = post

            }
        return feed

    }

}