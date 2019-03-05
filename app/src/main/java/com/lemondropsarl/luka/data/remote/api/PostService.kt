package com.lemondropsarl.luka.data.remote.api

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.storage.UploadTask
import com.lemondropsarl.luka.data.remote.model.Post

interface PostService {
    fun getQuery(id:String) : Query
    fun onsaveImage(uri: Uri, mimeType: String): UploadTask
    fun onSyncPost(post: Post, id: String): Task<DocumentReference>

}