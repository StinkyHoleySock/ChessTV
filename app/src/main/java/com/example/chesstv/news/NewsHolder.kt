package com.example.chesstv.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.squareup.picasso.Picasso

class NewsHolder(view: View): RecyclerView.ViewHolder(view){

    private val textTitle: TextView = itemView.findViewById(R.id.tv_news_title)
    private val date: TextView = itemView.findViewById(R.id.tv_date)
    private val imageTitle: ImageView = itemView.findViewById(R.id.iv_news_title)

    fun bind(news: News) {

        textTitle.text = news.title
        date.text = news.date

        Picasso.with(itemView.context)
            .load(news.imageLink)
            .into(imageTitle)
    }

}