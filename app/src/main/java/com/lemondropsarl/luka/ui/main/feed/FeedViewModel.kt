package com.lemondropsarl.luka.ui.main.feed

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import com.lemondropsarl.luka.data.repository.FeedRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    repo: FeedRepository
) : ViewModel() {

    val basicQuery: Query = repo.getPagingQuery()

    fun refresh() {}
    fun retry() {}

}