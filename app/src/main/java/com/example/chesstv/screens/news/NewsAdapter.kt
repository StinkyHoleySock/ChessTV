package com.example.chesstv.screens.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chesstv.R
import com.example.chesstv.databinding.NewsItemBinding
import com.example.chesstv.model.News
import com.example.chesstv.screens.news.details.NewsDetailsFragment

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    var news: List<News> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val itemNews = news[position]

        with(holder.binding) {

            tvNewsTitle.text = itemNews.title
            tvDate.text = itemNews.date

            Glide.with(ivNewsTitle.context)
                .load(itemNews.imageLink)
                .into(ivNewsTitle)

            this.cardView.setOnClickListener {
                cardView.findNavController().navigate(
                    R.id.action_homeFragment_to_newsDetailsFragment,
                    bundleOf(NewsDetailsFragment.newsKey to itemNews.linkDetails)
                )
            }
        }


    }

    override fun getItemCount() = news.size

    class NewsViewHolder(
        val binding: NewsItemBinding
        ) : RecyclerView.ViewHolder(binding.root)

}