package com.p1.loginlayout

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.p1.loginlayout.DB.ChampionsDBHelper
import com.p1.loginlayout.fragments.EditFragment
import com.p1.loginlayout.fragments.HomeFragment
import com.p1.loginlayout.fragments.MyListFragment

// BottomNavigation is the second activity of the app
class BottomNavigation : AppCompatActivity() {
    companion object {
        lateinit var dbHelper: ChampionsDBHelper
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)


        dbHelper = ChampionsDBHelper(this)

        // When the user clicks on an item in the bottom navigation, the corresponding fragment is loaded
        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when(item.itemId){
                R.id.nav_home -> {
                    loadFragment(HomeFragment(dbHelper))
                    true
                }
                R.id.nav_edit -> {
                    loadFragment(EditFragment(dbHelper))
                    true
                }
                R.id.nav_list -> {
                    loadFragment(MyListFragment(dbHelper))
                    true
                }
                else -> {false}
            }
        }
        loadFragment(HomeFragment(dbHelper))
    }
    // Function to load the fragments
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}