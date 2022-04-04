package com.example.chesstv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.example.chesstv.comments.CommentAdapter
import com.example.chesstv.comments.Comments
import java.util.*
import kotlin.concurrent.schedule

class WatchStreamFragment: Fragment(R.layout.fragment_watch_stream){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_watch_stream, container, false)

        val commentRecyclerView: RecyclerView = view.findViewById(R.id.comment_recycler_view)
        commentRecyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = CommentAdapter(fillData() as MutableList<Comments>)

        commentRecyclerView.adapter = adapter

        return view
    }

    private fun fillData(): List<Comments> {

        val data = mutableListOf<Comments>()
        val comments = resources.getStringArray(R.array.comments) // Получаем массив комментариев


        for (i in 0..20) {
            val random = comments.indices.random() // Индекс случайного комментария
            data.add(Comments(1,"User Name", comments[random]))
            //Timer().schedule(1000){ // Каждую секунду будет генерироваться новый комментарий
            //    for (j in 0..20) {
            //
            //    }
            //}
        }

        return data
    }

}