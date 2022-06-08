package com.example.chesstv.screens.profile

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CurrentUser {

    private val user = Firebase.auth.currentUser

    fun loadAvatar(imageContainer: ImageView) {
        user?.let {

            val photoUrl = user.photoUrl

            if (photoUrl != null) {
                Glide.with(imageContainer)
                    .load(photoUrl)
                    .circleCrop()
                    .into(imageContainer)
            }
        }
    }

    fun loadUsername(username: TextView) {
        user?.let {

            val name = user.displayName
            username.text = name

        }
    }

    fun loadEmail(email: TextView) {
        user?.let {
            val userEmail = user.email
            email.text = userEmail
        }
    }
}



