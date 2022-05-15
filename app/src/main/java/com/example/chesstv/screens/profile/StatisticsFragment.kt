package com.example.chesstv.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentProfileStatisticsBinding

class StatisticsFragment: Fragment(R.layout.fragment_profile_statistics) {

    private lateinit var binding: FragmentProfileStatisticsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }
}