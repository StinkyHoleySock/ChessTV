package com.example.chesstv.streams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.example.chesstv.screens.WatchStreamFragment
import com.example.chesstv.streams.StreamAdapter
import com.example.chesstv.streams.Streams
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.schedule


class StreamFragment: Fragment(R.layout.fragment_stream), StreamAdapter.MyClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stream, container, false)

        val streamsRecyclerView: RecyclerView = view.findViewById(R.id.streams_recycler_view)

        val listOfStreams = mutableListOf(
            Streams(1,"Пробный стрим №1", "Краткое описание трансляции", R.drawable.ic_stream_1, 1),
            Streams(1,"Пробный стрим №2", "Краткое описание трансляции", R.drawable.ic_stream_2, 1),
            Streams(1,"Пробный стрим №3", "Краткое описание трансляции", R.drawable.ic_stream_3, 1),
            Streams(1,"Пробный стрим №4", "Краткое описание трансляции", R.drawable.ic_stream_4, 1),
            Streams(1,"Пробный стрим №5", "Краткое описание трансляции", R.drawable.ic_stream_5, 1),
            Streams(1,"Пробный стрим №6", "Краткое описание трансляции", R.drawable.ic_stream_6, 1)

        )

        streamsRecyclerView.layoutManager = LinearLayoutManager(context)
        streamsRecyclerView.adapter = StreamAdapter(listOfStreams, this)

        return view

    }

    fun delay() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
    }

    override fun onClick(position: Int) {
        findNavController().navigate(
            R.id.action_streamFragment_to_watchStreamFragment,
            bundleOf(WatchStreamFragment.streamId to position)
        )
    }
}