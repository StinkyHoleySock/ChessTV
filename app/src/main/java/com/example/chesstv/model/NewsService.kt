package com.example.chesstv.model

import kotlinx.coroutines.*
import okhttp3.internal.notifyAll
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

typealias NewsListener = (news_item: List<News>) -> Unit

class NewsService {

    private val listNews = mutableListOf<News>()
    private var isLoaded = false
    private val listeners = mutableSetOf<NewsListener>()

    suspend fun loadNews(): MutableList<News> {

        withContext(Dispatchers.IO) {

            val url = "https://ria.ru/chess/"
            val doc: Document = Jsoup.connect(url).get()
            val news: Elements = doc.select("div[class=list-item]")
            for (i in 0 until news.size) {
                val title = news.select("a.list-item__title.color-font-hover-only")
                    .eq(i)
                    .text()
                val imageLink = news.select("a.list-item__image")
                    .select("picture")
                    .select("img")
                    .eq(i)
                    .attr("src")
                val date = news.select("div[class=list-item__info]")
                    .select("div[class=list-item__date]")
                    .eq(i)
                    .text()
                val linkToNews = news.select("a.list-item__title.color-font-hover-only")
                    .eq(i)
                    .attr("href")

                listNews.add(News(i, title, imageLink, date, linkToNews))
            }
        }
        isLoaded = true
        notifyChanges()
        return listNews
    }

    fun addListener(listener: NewsListener) {
        listeners.add(listener)
        if (isLoaded)
            listener.invoke(listNews)
    }

    private fun notifyChanges() {
        if (!isLoaded) return
        listeners.forEach { it.invoke(listNews)}
    }
}