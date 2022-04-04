package com.example.chesstv.screens

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.example.chesstv.comments.CommentAdapter
import com.example.chesstv.comments.Comments
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.timerTask


class WatchStreamFragment: Fragment(R.layout.fragment_watch_stream){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_watch_stream, container, false)

        //RecyclerView
        val commentRecyclerView: RecyclerView = view.findViewById(R.id.comment_recycler_view)
        commentRecyclerView.layoutManager = LinearLayoutManager(context)

        commentRecyclerView.adapter = CommentAdapter(fillData())



        //Часть с VideoView
        val videoView = view.findViewById<VideoView>(R.id.vv_stream)
        val myVideoUri =
            Uri.parse("android.resource://" + activity?.packageName + "/" + R.raw.video_exmple)
        videoView.setVideoURI(myVideoUri)
        videoView.start()


        return view
    }

    private fun fillData(): List<Comments> {

        val data = mutableListOf<Comments>()
        val comments = resources.getStringArray(R.array.comments) // Получаем массив комментариев

        //for (i in 1..20){

          //Timer().schedule(timerTask {

                data.add(Comments(1, "User Name",
                    comments[Random().nextInt(comments.size)]))

          //}, 2000)

          adapter.notifyItemInserted(data.size - 1)

        //}





        return data
    }

}