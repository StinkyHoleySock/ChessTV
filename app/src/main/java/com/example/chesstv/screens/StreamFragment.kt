package com.example.chesstv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.example.chesstv.streams.StreamAdapter
import com.example.chesstv.streams.Streams

private lateinit var adapter: StreamAdapter
private val listStreams = mutableListOf<Streams>()

class StreamFragment: Fragment(R.layout.fragment_stream) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stream, container, false)

        val streamsRecyclerView: RecyclerView = view.findViewById(R.id.streams_recycler_view)
        streamsRecyclerView.layoutManager = LinearLayoutManager(context)

        streamsRecyclerView.adapter = StreamAdapter(fillData() as MutableList<Streams>)

        return view
    }

    fun fillData(): List<Streams> {
        val data = mutableListOf<Streams>()
        for (i in 0..10) {
            data.add(Streams(1,"Some title 1", "Video description 1", R.drawable.ic_example_avatar, 1))
        }
        return data
    }
}