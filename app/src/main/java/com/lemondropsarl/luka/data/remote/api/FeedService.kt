package com.lemondropsarl.luka.data.remote.api

import com.google.firebase.firestore.Query

interface FeedService {
    //implement operation to call the database
    fun getPagingQuery(): Query

}