package com.example.chesstv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstv.R


class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private lateinit var profileRecyclerView: RecyclerView
    private var adapter: ProfileItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        profileRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {
        val profileItems = ArrayList<String>()
        profileItems.add("Сообщения")
        profileItems.add("Профиль")
        profileItems.add("Конфиденциальность")
        profileItems.add("Подписки")
        profileItems.add("Статистика")
        profileItems.add("Настройки")
        adapter = ProfileItemAdapter(profileItems)
        profileRecyclerView.adapter = adapter
    }

    private inner class ProfileItemHolder(view: View): RecyclerView.ViewHolder(view){

        val itemTextView: TextView = itemView.findViewById(R.id.tv_item_title)
    }

    private inner class ProfileItemAdapter(var items: List<String>)
        : RecyclerView.Adapter<ProfileItemHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemHolder {
            val view = layoutInflater.inflate(R.layout.custom_profile_item, parent, false)
            return ProfileItemHolder(view)
        }

        override fun onBindViewHolder(holder: ProfileItemHolder, position: Int) {
            val item = items[position]
            holder.itemTextView.text = item

        }

        override fun getItemCount() = items.size
    }
}