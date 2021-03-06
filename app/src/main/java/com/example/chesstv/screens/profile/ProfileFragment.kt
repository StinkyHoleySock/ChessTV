package com.example.chesstv.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

            CurrentUser().loadUsername(tvUsername)
            CurrentUser().loadAvatar(imageContainer)
            CurrentUser().loadEmail(tvUserEmail)

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