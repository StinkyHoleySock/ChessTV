package com.example.chesstv

import android.app.Application
import com.example.chesstv.model.CommentsService

class App: Application() {

    val commentsService = CommentsService()
}