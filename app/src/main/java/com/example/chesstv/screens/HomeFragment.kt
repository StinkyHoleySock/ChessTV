package com.example.chesstv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.news.News
import com.example.chesstv.news.NewsAdapter
import com.example.chesstv.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

private lateinit var adapter: NewsAdapter
private val listNews = mutableListOf<News>()

/*Тут всё надо исправлять. Корутина не должна быть тут
  Перенести логику во вьюмодель, использовать ливдату */

class HomeFragment: Fragment(R.layout.fragment_home) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val newsRecyclerView: RecyclerView = view.findViewById(R.id.news_recycler_view)
        newsRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = NewsAdapter()
        newsRecyclerView.adapter = adapter

        // Запуск корутины
        GlobalScope.launch {
            getData()
        }

        return view
    }

    private fun getData(): MutableList<News> {

        try {
            val url = "https://ria.ru/chess/"
            val doc: Document = Jsoup.connect(url).get()

            val news: Elements = doc.select("div[class=list-item]") // Проверить
            val newsSize: Int = news.size

            for (i in 0 until newsSize) {
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

            GlobalScope.launch(Dispatchers.Main) {
                adapter.set(listNews)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listNews
    }
}