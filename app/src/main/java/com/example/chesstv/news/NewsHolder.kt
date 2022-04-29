package com.example.chesstv.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chesstv.R

class NewsHolder(view: View): RecyclerView.ViewHolder(view){

    private val textTitle: TextView = itemView.findViewById(R.id.tv_news_title)
    private val date: TextView = itemView.findViewById(R.id.tv_date)
    private val imageTitle: ImageView = itemView.findViewById(R.id.iv_news_title)
    private var link = "link"

    init {
        itemView.setOnClickListener {
           itemView.findNavController().navigate(
               R.id.action_homeFragment_to_newsDetailsFragment,
               bundleOf(NewsDetailsFragment.newsKey to link)
           )
        }
    }

    fun bind(news: News) {

        textTitle.text = news.title
        date.text = news.date

        Glide.with(itemView.context)
            .load(news.imageLink)
            .into(imageTitle)

        link = news.linkDetails
    }

}