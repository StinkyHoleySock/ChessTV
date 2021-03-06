package com.example.chesstv.screens.streams.watch_streams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstv.model.Comment
import com.example.chesstv.model.CommentsListener
import com.example.chesstv.model.CommentsService

class CommentsViewModel(
    private val commentsService: CommentsService
): ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    private val listener: CommentsListener = {
        _comments.value = it
    }

    init {
        loadComments()
    }

    override fun onCleared() {
        super.onCleared()
        /* Отписываемся от слушателя (от модели) чтобы избежать утечки памяти:
        CommentsService - синглтон, его ЖЦ больше, чем ЖЦ CommentsViewModel */
        commentsService.removeListener(listener)
    }

    private fun loadComments() {
        commentsService.loadComments()
    }

    fun deleteComment(comment: Comment) {
        commentsService.deleteComment(comment)
    }
}

