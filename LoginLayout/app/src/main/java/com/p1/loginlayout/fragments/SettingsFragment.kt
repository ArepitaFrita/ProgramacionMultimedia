package com.p1.loginlayout.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.p1.loginlayout.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val language = findPreference<Preference>("language")
        language?.setOnPreferenceChangeListener { preference, newValue ->
            val editor: SharedPreferences.Editor = preference.context.getSharedPreferences("config", 0).edit()
            editor.putString("language", newValue.toString())
            editor.apply()
            true

        }
    }

}