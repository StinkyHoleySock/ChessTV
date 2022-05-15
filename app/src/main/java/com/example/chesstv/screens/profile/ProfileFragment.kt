package com.example.chesstv.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settings.setOnClickListener {
            findNavController().navigate(
                R.id.action_profileFragment_to_settingsFragment
            )
        }


        with(binding){

            // todo hardcode // navigation to empty fragment
            navigateToEmpty(listOf(privacy, profile, confidentiality, subscriptions))

            statistics.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_statisticsFragment)
            }


            settings.setOnClickListener {
                findNavController().navigate(
                    R.id.action_profileFragment_to_settingsFragment
                )
            }

            val user = Firebase.auth.currentUser

            user?.let {
                // Name, email address, and profile photo Url

                val name = user.displayName
                val email = user.email
                val photoUrl = user.photoUrl


                tvUsername.text = name
                tvUserEmail.text = email

                if (photoUrl != null) {
                    activity?.let {
                        Glide.with(it)
                            .load(photoUrl)
                            .circleCrop()
                            .into(imageContainer)
                    }
                }

            }

        }


    }

    private fun navigateToEmpty(items: List<LinearLayout>) {
        for (item in items) {
            item.setOnClickListener {
                findNavController().navigate(
                    R.id.action_profileFragment_to_fragmentEmpty
                )
            }
        }
    }

}