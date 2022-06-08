package com.example.chesstv.screens.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R
import com.example.chesstv.model.Favorite

class FavoriteAdapter(private val favorites: MutableList<Favorite>
    ) : RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {

    inner class FavoriteHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.favorite_image)
        val itemDescription: TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_item, parent, false)
        return FavoriteHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteHolder, position: Int) {
        val item = favorites[position]
        holder.apply {
            itemImage.setImageResource(item.image)
            itemDescription.text = item.description
        }
    }

    override fun getItemCount() = favorites.size


}