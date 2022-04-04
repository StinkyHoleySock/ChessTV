package com.example.chesstv.comments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommentsViewModel: ViewModel() {

    val commentsList: MutableLiveData<List<Comments>> = MutableLiveData()

    init {
        //commentsList.value =
    }
}