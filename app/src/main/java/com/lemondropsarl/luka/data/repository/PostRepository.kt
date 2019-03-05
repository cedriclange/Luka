package com.lemondropsarl.luka.data.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.lemondropsarl.luka.data.remote.api.PostService
import com.lemondropsarl.luka.data.remote.model.Post
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostRepository @Inject constructor(
    private val firestore: FirebaseFirestore

) : PostService {
    private val storage = FirebaseStorage.getInstance().getReference("uploads")

    companion object {

        const val USERS = "users"

        const val USERPOST = "user-post"
    }

    override fun getQuery(id: String): Query {
        return firestore.collection(USERS).document(id).collection(USERPOST)
            .orderBy("createdAt", Query.Direction.DESCENDING)
    }

    override fun onsaveImage(uri: Uri, mimeType: String): UploadTask {


        val reference = storage.child(
            System.currentTimeMillis().toString()
                    + "." + mimeType
        )

        return reference.putFile(uri)
    }

    override fun onSyncPost(post: Post, id: String): Task<DocumentReference> {
        //get Document reference
        val docReference = firestore.collection(USERS).document(id)
            .collection(USERPOST)
        return docReference.add(post)

    }


}
