package com.lemondropsarl.luka.ui.main.post

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.storage.UploadTask
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.data.repository.PostRepository
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val repo: PostRepository
) : ViewModel() {

    var imageUri: Uri? = null





    fun getQuery(uid: String) : Query{

        return repo.getQuery(uid)

    }

    fun uploadImage(uri: Uri, mimeType: String): UploadTask {
        return repo.onsaveImage(uri, mimeType)
    }

    fun syncFirestore(post: Post, id: String): Task<DocumentReference> {
        //start to save the image
        return repo.onSyncPost(post, id)
        //sync database
    }
}