package com.example.chesstv.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentProfileBinding

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

        // todo this data must be from firebase
        with(binding){
            activity?.let {
                Glide.with(it)
                    .load(R.drawable.ic_example_avatar)
                    .circleCrop()
                    .into(imageContainer)
            }

            tvUsername.text = "Дмитрий Первов"
            tvCity.text = "Углич"
        }

    }
}