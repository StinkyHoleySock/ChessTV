package com.example.chesstv.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstv.screens.adapter
import java.util.*
import kotlin.concurrent.timerTask

class CommentsViewModel: ViewModel() {

  private val _comments = MutableLiveData<List<Comments>>()
  val comments: LiveData<List<Comments>> = _comments

    fun fillData(): List<Comments> {


      val data = mutableListOf<Comments>()


      for (i in 1..20){

          //Timer().schedule(timerTask {
          //}, 2000)
        data.add(Comments(1, "User Name", "Some comment"))

        adapter.notifyItemInserted(data.size - 1)
      }

      return data
  }

    init {
        _comments.value = fillData()
    }
}