package com.example.chesstv.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chesstv.model.News
import com.example.chesstv.model.NewsService
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsService: NewsService
): ViewModel() {

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    init {
        viewModelScope.launch {
            newsService.loadNews()
        }
    }



}