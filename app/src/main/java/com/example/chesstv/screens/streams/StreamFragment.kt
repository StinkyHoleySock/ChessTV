package com.example.chesstv.screens.streams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.example.chesstv.model.Streams
import com.example.chesstv.screens.streams.watch_streams.WatchStreamFragment


class StreamFragment: Fragment(R.layout.fragment_stream), StreamAdapter.MyClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_stream, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val streamsRecyclerView: RecyclerView = view.findViewById(R.id.streams_recycler_view)

        val listOfStreams = mutableListOf(
            Streams(1,"Игра со зрителями", "С небольшим призовым фондом", R.drawable.ic_stream_1, 1, IMAGES[1]),
            Streams(1,"Шахматный стрим", "Турнир и игра со зрителями", R.drawable.ic_stream_2, 1, IMAGES[1]),
            Streams(1,"Комментирует Карпов!", "Непомнящий - Гири", R.drawable.ic_stream_3, 1, IMAGES[1]),
            Streams(1,"5 ключевых навыков", "Сильнейших шахматистов", R.drawable.ic_stream_4, 1, IMAGES[1]),
            Streams(1,"Обзор третьего тура", "Турнир претендентов", R.drawable.ic_stream_5, 1, IMAGES[1]),
            Streams(1,"Карлсен - Накамура!", "Стрим Chess24", R.drawable.ic_stream_6, 1, IMAGES[1])

        )

        streamsRecyclerView.layoutManager = LinearLayoutManager(context)
        streamsRecyclerView.adapter = StreamAdapter(listOfStreams, this)
    }


    override fun onClick(position: Int) {
        findNavController().navigate(
            R.id.action_streamFragment_to_watchStreamFragment,
            bundleOf(WatchStreamFragment.streamId to position)
        )
    }

    companion object {
        private val IMAGES = mutableListOf(
            "https://avatarko.ru/img/kartinka/25/muzhchina_serial_dengi_24933.jpg",
            "https://avatarko.ru/img/kartinka/14/serial_13916.jpg",
            "https://avatarko.ru/img/kartinka/16/film_gnom_15452.jpg",
            "https://avatarko.ru/img/kartinka/15/film_gnom_14558.jpg",
            "https://avatarko.ru/img/kartinka/11/film_muzhchina_galstuk_10235.jpg",
            "https://avatarko.ru/img/kartinka/11/film_elf_Middle-earth_Arwen_10847.jpg"
        )
    }
}