package com.example.chesstv.screens.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chesstv.R
import com.example.chesstv.databinding.FragmentProfileSettingsBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment: Fragment(R.layout.fragment_profile_settings) {

    private lateinit var binding: FragmentProfileSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            //заглушки
            navigateToEmpty(notifications)
            navigateToEmpty(privacy)

            profile.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_fragmentProfileData)
            }


            switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    SettingPreferences(view.context).darkMode = 0
                }  else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    SettingPreferences(view.context).darkMode = 1
                }
            }

            tvLogout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                // TODO: navigate
            }
        }
    }

    private fun navigateToEmpty(item: LinearLayout) {
        item.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_fragmentEmpty
            )
        }
    }
}