package com.example.chesstv

import android.app.Application
import com.example.chesstv.model.CommentsService
import com.example.chesstv.model.NewsService

class App: Application() {

    val commentsService = CommentsService()
    val newsService = NewsService()
}