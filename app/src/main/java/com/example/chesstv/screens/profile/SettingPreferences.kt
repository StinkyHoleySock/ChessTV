package com.example.chesstv.screens.profile

import android.content.Context
import androidx.preference.PreferenceManager

class SettingPreferences(context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var darkMode = preferences.getInt(DARK_STATUS, 0)
        set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()

    companion object {
        private const val DARK_STATUS = "com.example.chesstv.DARK_STATUS"
    }
}