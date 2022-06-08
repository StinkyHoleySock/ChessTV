package com.example.chesstv.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.chesstv.R
import com.example.chesstv.model.Favorite

class FavoriteFragment: Fragment(R.layout.fragment_favorite) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteRecyclerView: RecyclerView = view.findViewById(R.id.favorite_recycler_view)

        val listOfFavorites = mutableListOf(
            Favorite(R.drawable.ic_stream_1, "Some text"),
            Favorite(R.drawable.ic_stream_1, "Some text"),
            Favorite(R.drawable.ic_stream_1, "Some text"),
            Favorite(R.drawable.ic_stream_1, "Some text"),
            Favorite(R.drawable.ic_stream_1, "Some text")
        )


    }

}