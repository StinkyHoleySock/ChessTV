package com.example.chesstv.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R

class CommentAdapter(private val comments: List<Comments>):
    RecyclerView.Adapter<CommentAdapter.CommentHolder>() {


    inner class CommentHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val username: TextView = itemView.findViewById(R.id.tv_comment_username)
        val content: TextView = itemView.findViewById(R.id.tv_comment)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_item, parent, false)
        return CommentHolder(view)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val item = comments[position]
        holder.apply {
            username.text = item.username
            content.text = item.content
        }

    }

    override fun getItemCount() = comments.size


}