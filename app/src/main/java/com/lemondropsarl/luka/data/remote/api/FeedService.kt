package com.lemondropsarl.luka.data.remote.api

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

interface FeedService {
    //implement operation to call the database
    fun getPagingQuery(): Query

    fun getById(id: String): Task<DocumentSnapshot>

}