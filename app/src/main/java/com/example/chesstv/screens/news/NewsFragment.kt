package com.example.chesstv.screens.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chesstv.App
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentHomeBinding
import com.example.chesstv.model.NewsListener
import com.example.chesstv.model.NewsService
import com.example.chesstv.screens.factory

class NewsFragment: Fragment(R.layout.fragment_home) {

    private lateinit var adapter: NewsAdapter
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: NewsViewModel by viewModels{ factory() }

    private val newsService: NewsService
        get() = (activity?.applicationContext as App).newsService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    private val usersListener: NewsListener = {
        adapter.news = it
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter()

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.news = it
        }

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.newsRecyclerView.adapter = adapter

        newsService.addListener(usersListener)
    }

}