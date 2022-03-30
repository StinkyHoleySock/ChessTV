package com.example.chesstv.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R

class NewsAdapter: RecyclerView.Adapter<NewsHolder>(){

    private val news = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount() = news.size


    fun set(list: MutableList<News>) {
        this.news.clear()
        this.news.addAll(list)

        notifyDataSetChanged()

    }

}