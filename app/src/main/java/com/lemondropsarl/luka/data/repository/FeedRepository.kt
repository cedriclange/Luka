package com.lemondropsarl.luka.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.lemondropsarl.luka.data.remote.api.FeedService
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class FeedRepository @Inject constructor(
    @Named("posts")
    private val postCollection: CollectionReference,
    private val firestore: FirebaseFirestore
) : FeedService {
    override fun getFeedById(id: String): Task<DocumentSnapshot> {
        return postCollection.document(id).get()
    }


    //get data for paging recycler
    override fun getPagingQuery(): Query {
        return postCollection.orderBy("createdAt", Query.Direction.DESCENDING)
    }


}