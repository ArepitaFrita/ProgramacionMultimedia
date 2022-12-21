package com.p1.loginlayout

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.p1.loginlayout.DB.ChampionsDBHelper
import com.p1.loginlayout.databinding.ActivityBottomNavigationBinding
import com.p1.loginlayout.fragments.HomeFragment
import com.p1.loginlayout.fragments.InsertFragment
import com.p1.loginlayout.fragments.MyListFragment
import com.p1.loginlayout.fragments.SettingsFragment

// BottomNavigation is the second activity of the app
class BottomNavigation : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    companion object {
        lateinit var dbHelper: ChampionsDBHelper

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                loadFragment(SettingsFragment())
            }
            R.id.logout -> {
                val sharedPreference = getSharedPreferences("config", Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.clear()
                editor.apply()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)



        dbHelper = ChampionsDBHelper(this)

        // When the user clicks on an item in the bottom navigation, the corresponding fragment is loaded
        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment(dbHelper))
                    true
                }
                R.id.nav_edit -> {
                    loadFragment(InsertFragment(dbHelper))
                    true
                }
                R.id.nav_list -> {
                    loadFragment(MyListFragment(dbHelper))
                    true
                }
                else -> {
                    false
                }
            }
        }
        loadFragment(HomeFragment(dbHelper))
    }

    // Function to load the fragments
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}