package com.lemondropsarl.luka.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.lemondropsarl.luka.data.remote.api.PostService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : PostService {

    companion object {
        const val USERS = "users"
        const val USERPOST = "user-post"
    }

    override fun getQuery(id:String): Query {
        return firestore.collection(USERS).document(id).collection(USERPOST)
            .orderBy("createdAt", Query.Direction.DESCENDING)
    }

}