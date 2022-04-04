package com.example.chesstv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.example.chesstv.streams.StreamAdapter
import com.example.chesstv.streams.Streams


class StreamFragment: Fragment(R.layout.fragment_stream), StreamAdapter.MyClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stream, container, false)

        val streamsRecyclerView: RecyclerView = view.findViewById(R.id.streams_recycler_view)

        val listOfStreams = mutableListOf(
            Streams(1,"Some title 1", "Video description 1", R.drawable.ic_example_avatar, 1),
            Streams(1,"Some title 1", "Video description 1", R.drawable.ic_example_avatar, 1),
            Streams(1,"Some title 1", "Video description 1", R.drawable.ic_example_avatar, 1),
            Streams(1,"Some title 1", "Video description 1", R.drawable.ic_example_avatar, 1),
            Streams(1,"Some title 1", "Video description 1", R.drawable.ic_chess_icon, 1)
        )

        streamsRecyclerView.layoutManager = LinearLayoutManager(context)
        streamsRecyclerView.adapter = StreamAdapter(listOfStreams, this)

        return view
    }

    //private fun fillData(): List<Streams> {
    //    val data = mutableListOf<Streams>()
    //    for (i in 0..10) {
    //        data.add(Streams(1,"Some title 1", "Video description 1", R.drawable.ic_example_avatar, 1))
    //    }
    //    return data
    //}

    override fun onClick(position: Int) {
        findNavController().navigate(R.id.action_streamFragment_to_watchStreamFragment)
    }
}