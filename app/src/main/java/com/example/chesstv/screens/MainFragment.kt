package com.example.chesstv.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        val navHost = childFragmentManager.findFragmentById(R.id.fragment_container_view)
            as NavHostFragment

        val navController = navHost.navController

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

//        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
//
//        val navController = (childFragmentManager.findFragmentById(R.id.fragment_container_view)
//                as NavHostFragment).navController
//
//
//        NavigationUI.setupWithNavController(bottomNavigationView, navController)
//
//        //bottomNavigationView?.setupWithNavController(navController)
    }
}