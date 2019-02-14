package com.lemondropsarl.luka.data.remote.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Post(
    var title: String = "",
    var town: String = "",
    var area: String = "",
    var rooms: Int = 0,
    var price: Double = 0.0,
    var garantie: Double = 0.0,
    var postUrl: String = "",
    var specification: String = "",
    @ServerTimestamp
    var createdAt: Date? = null
)