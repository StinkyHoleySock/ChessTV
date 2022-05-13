package com.example.chesstv.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        val navHost = childFragmentManager.findFragmentById(R.id.fragment_container_view)
            as NavHostFragment

        val navController = navHost.navController

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.home, R.id.streams, R.id.favorite, R.id.profile
        ))

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

    }
}