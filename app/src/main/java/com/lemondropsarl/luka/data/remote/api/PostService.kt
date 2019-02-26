package com.lemondropsarl.luka.data.remote.api

import com.google.firebase.firestore.Query

interface PostService {
    fun getQuery(id:String) : Query

}