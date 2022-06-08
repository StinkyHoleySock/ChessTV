package com.example.chesstv.screens.streams.watch_streams

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chesstv.App
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentWatchStreamBinding
import com.example.chesstv.model.Comment
import com.example.chesstv.model.CommentsListener
import com.example.chesstv.model.CommentsService
import com.example.chesstv.screens.factory
import com.example.chesstv.screens.profile.CurrentUser
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.firebase.storage.FirebaseStorage


class WatchStreamFragment: Fragment(R.layout.fragment_watch_stream){

    private lateinit var adapter: CommentAdapter
    private lateinit var binding: FragmentWatchStreamBinding

    // Получаем доступ к viewModel с помощью делегата, в аргумент - фабрику вьюмоделей
    private val viewModel: CommentsViewModel by viewModels { factory() }

    private val commentsService: CommentsService
        get() = (activity?.applicationContext as App).commentsService


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWatchStreamBinding.inflate(inflater, container, false)


        Glide.with(binding.imageChannel.context)
            .load("https://avatarko.ru/img/kartinka/14/serial_13916.jpg")
            .centerCrop()
            .into(binding.imageChannel)

        return binding.root
    }



    private val usersListener: CommentsListener = {
        adapter.comments = it
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        CurrentUser().loadAvatar(binding.imageProfile)


        // Установка RecyclerView
        adapter = CommentAdapter(object : CommentActionListener {

            override fun onCommentDelete(comment: Comment) {
                viewModel.deleteComment(comment)
            }

            override fun onUserDetails(comment: Comment) {
                // todo navigation on usersScreen
                Toast.makeText(context, "User: ${comment.name}", Toast.LENGTH_SHORT).show()
            }

        })

        binding.imageChannel.setOnClickListener {
            findNavController().popBackStack()
        }

        //Подписка на LiveData
        viewModel.comments.observe(viewLifecycleOwner) {
            adapter.comments = it
        }

        binding.commentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.commentRecyclerView.adapter = adapter

        commentsService.addListener(usersListener)

        // TODO EXOPLAYER

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


    }

    override fun onDestroyView() {
        super.onDestroyView()

        commentsService.removeListener(usersListener)
    }

    companion object {
        const val streamId = "STREAM_ID"
    }


}