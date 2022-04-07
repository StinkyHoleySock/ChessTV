package com.example.chesstv.streams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R

class StreamAdapter(private val streams: MutableList<Streams>, val listener: MyClickListener):
    RecyclerView.Adapter<StreamAdapter.StreamHolder>() {

    inner class StreamHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val videoTitle: TextView = itemView.findViewById(R.id.tv_video_title)
        val videoDescription: TextView = itemView.findViewById(R.id.tv_video_description)
        val imagePreview: ImageView = itemView.findViewById(R.id.iv_preview)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                listener.onClick(position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stream_item, parent, false)
        return StreamHolder(view)
    }

    override fun onBindViewHolder(holder: StreamHolder, position: Int) {
        val item = streams[position]
        holder.apply {
            videoTitle.text = item.title
            videoDescription.text = item.description
            imagePreview.setImageResource(item.image)
        }

    }

    override fun getItemCount() = streams.size

    interface MyClickListener {
        fun onClick(position: Int)
    }
}