package com.example.chesstv.screens

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chesstv.App
import com.example.chesstv.screens.news.NewsViewModel
import com.example.chesstv.screens.streams.watch_streams.CommentsViewModel
import java.lang.IllegalStateException

class ViewModelFactory(private val app: App) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            CommentsViewModel::class.java -> {
                CommentsViewModel(app.commentsService)
            }
            NewsViewModel::class.java -> {
                NewsViewModel(app.newsService)
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}
fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)



