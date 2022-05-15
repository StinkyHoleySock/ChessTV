package com.example.chesstv

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentContainerView
import com.example.chesstv.databinding.ActivityMainBinding
import com.example.chesstv.screens.profile.settings.SettingPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.my_palette_1))

        checkTheme()

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
    

    private fun checkTheme() {
        when (SettingPreferences(this).darkMode) {
            0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}