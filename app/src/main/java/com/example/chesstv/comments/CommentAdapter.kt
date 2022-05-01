package com.example.chesstv.comments

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chesstv.R
import com.example.chesstv.databinding.CommentItemBinding
import com.example.chesstv.model.Comment

interface CommentActionListener {

    fun onCommentDelete(comment: Comment)

    fun onUserDetails(comment: Comment)
}

class CommentAdapter(
    private val actionListener: CommentActionListener
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(), View.OnClickListener {

    var comments: List<Comment> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.imageMore.setOnClickListener(this)

        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val user = comments[position]
        with(holder.binding) {

            holder.itemView.tag = user
            imageMore.tag = user

            tvCommentUsername.text = user.name
            tvComment.text = user.content
            if (user.photo.isNotBlank()) {
                Glide.with(imageContainer.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_avatar)
                    .error(R.drawable.ic_avatar)
                    .into(imageContainer)
            } else {
                Glide.with(imageContainer.context).clear(imageContainer)
                imageContainer.setImageResource(R.drawable.ic_avatar)
            } // Set avatar
        }
    }

    override fun getItemCount() = comments.size

    class CommentViewHolder(
        val binding: CommentItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val user = v.tag as Comment
        when (v.id) {
            R.id.image_more -> {
                showPopupMenu(v) // Вызов контекстного меню, для действия над пользователем
            }
            else -> {
                actionListener.onUserDetails(user)
            }
        }
    }

    /*
    Вызов меню, для действия над пользователем.
    Функционал будет расширен в будущем, сейчас остаётся гибким
     */
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val user = view.tag as Comment

        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, context.getString(R.string.remove))
        // Для расширения, добавить аналогичные строки

        popupMenu.setOnMenuItemClickListener {
            if (it.itemId == ID_REMOVE) {
               actionListener.onCommentDelete(user) // Юзер находится в теге вью
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }
    /*
    Здесь хранятся уникальные идентификаторы действий над пользователями.
    Можно добавить, тем самым расширив функционал
     */
    companion object {
        private const val ID_REMOVE = 1
    }

}