package com.lemondropsarl.luka.data.remote.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Post(
    var title: String = "",
    var city: String = "",
    var area: String = "",
    var rooms: Int = 0,
    var price: Double = 0.0,
    var guaranty: Double = 0.0,
    var photoUrl: String = "",
    var specification: String = "",
    var author: String = "",
    @ServerTimestamp
    var createdAt: Date? = null
)