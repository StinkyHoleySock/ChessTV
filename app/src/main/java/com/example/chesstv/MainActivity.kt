package com.example.chesstv

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.chesstv.databinding.ActivityMainBinding
import com.example.chesstv.screens.MainFragment
import com.example.chesstv.screens.profile.settings.SettingPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth



    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.my_palette_1))

        checkTheme()
        checkNetworkConnection()
    }


    private fun checkTheme() {
        when (SettingPreferences(this).darkMode) {
            0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun checkNetworkConnection() {
        val networkConnection = NetworkConnection(applicationContext)

        networkConnection.observe(this) {
                isConnected ->
            if (isConnected) {
                binding.containerView.visibility = View.VISIBLE
                binding.noNetworkContainer.visibility = View.GONE
            } else {
                binding.containerView.visibility = View.GONE
                binding.noNetworkContainer.visibility = View.VISIBLE
            }
        }
    }



}