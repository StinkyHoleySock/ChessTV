package com.example.chesstv.screens.news.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentNewsDetailsBinding
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class NewsDetailsFragment: Fragment(R.layout.fragment_news_details), CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private lateinit var binding: FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        binding.shimmerFrameLayout.startShimmerAnimation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        job = launch(Dispatchers.IO) {
            getData()
        }

    }

    private fun getData() {
        try {

            val document = Jsoup.connect(arguments?.getString(newsKey)).get()
            val elements = document.select("div[class=endless__item-content page__width]")

            val title = elements
                .select("div[class=article__title]")
                .text()

            val imageLink = elements
                .select("img")
                .attr("src")

            val description = elements
                .select("div[class=article__text]")
                .text()

            job = launch {

                if (title.isNotEmpty()) {
                    binding.newsDetailsTitle.text = title.toString()
                }
                else binding.newsDetailsTitle.visibility = View.GONE

                binding.newsDetailsDescription.text = description.toString()

                activity?.let {
                    Glide.with(it)
                        .load(imageLink)
                        .into(binding.newsDetailsImage)
                }

                binding.shimmerFrameLayout.stopShimmerAnimation()
            }

        } catch (e: IOException) {
            Log.e("MES", e.message.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }

    companion object {
        const val newsKey = "LINK"
    }
}