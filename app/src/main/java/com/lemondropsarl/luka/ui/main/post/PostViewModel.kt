package com.lemondropsarl.luka.ui.main.post

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import com.lemondropsarl.luka.data.repository.PostRepository
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val repo: PostRepository
) : ViewModel() {



    fun getQuery(uid: String) : Query{

        return repo.getQuery(uid)
    }
}