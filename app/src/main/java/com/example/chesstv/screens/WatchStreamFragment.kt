package com.example.chesstv.screens

import android.app.Application
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.example.chesstv.comments.CommentAdapter
import com.example.chesstv.comments.Comments
import com.example.chesstv.comments.CommentsData
import com.example.chesstv.comments.CommentsViewModel
import java.util.*


class WatchStreamFragment: Fragment(R.layout.fragment_watch_stream){

    private lateinit var adapter: CommentAdapter

    private val commentsViewModel by lazy {
        ViewModelProvider(this)[CommentsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_watch_stream, container, false)

        //RecyclerView
        val commentRecyclerView: RecyclerView = view.findViewById(R.id.comment_recycler_view)
        commentRecyclerView.layoutManager = LinearLayoutManager(context)




            //commentsViewModel.comments.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
        //    commentsViewModel.comments.value
                //})
        commentRecyclerView.adapter = CommentAdapter(fillData())




        //Часть с VideoView

        val videoView = view.findViewById<VideoView>(R.id.vv_stream)

        // Инициализация видео. В реальном проекте будут парситься ссылки на ресурс
        val listStream = listOf(
            "android.resource://" + activity?.packageName + "/" + R.raw.video_example_1,
            "android.resource://" + activity?.packageName + "/" + R.raw.video_example_2,
            "android.resource://" + activity?.packageName + "/" + R.raw.video_example_3,
            "android.resource://" + activity?.packageName + "/" + R.raw.video_example_4,
            "android.resource://" + activity?.packageName + "/" + R.raw.video_example_5,
            "android.resource://" + activity?.packageName + "/" + R.raw.video_example_6
        )

        val myVideoUri = Uri.parse(listStream[arguments?.getInt(streamId)!!])

        videoView.apply {
            setVideoURI(myVideoUri)
            requestFocus(0)
            setMediaController(MediaController(context))
            start()
        }

        return view
    }

    private fun fillData(): List<Comments> {

        val data = mutableListOf<Comments>()


        for (i in 1..20){

            val randomComment = CommentsData.exampleComments
            val randomName = CommentsData.exampleUserNames
            //Timer().schedule(timerTask {
            //}, 2000)
            data.add(Comments(1,
                randomName[Random().nextInt(randomName.size)],
                randomComment[Random().nextInt(randomComment.size)]
            ))
            com.example.chesstv.screens.adapter.notifyItemInserted(data.size - 1)
        }

        return data
    }


    companion object {
        const val streamId = "STREAM_ID"
    }


}