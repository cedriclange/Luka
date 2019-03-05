package com.lemondropsarl.luka.di.module

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
@Module
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        FirebaseFirestore.setLoggingEnabled(true)
        return FirebaseFirestore.getInstance()
    }



    @Singleton
    @Provides
    @Named("posts")
    fun providePosts(): CollectionReference {
        FirebaseFirestore.setLoggingEnabled(true)
        return FirebaseFirestore.getInstance().collection("posts")

    }
    //provide different service


}



