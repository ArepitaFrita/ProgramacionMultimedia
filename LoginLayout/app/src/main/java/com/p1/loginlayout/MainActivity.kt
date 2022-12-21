package com.p1.loginlayout

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.p1.loginlayout.DB.ChampionsDBHelper
import java.util.*

// Main Activity of the app
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setAppLocale("en")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btnSignIn)
        val userText: EditText = findViewById(R.id.txtUserName)
        val userPw: EditText = findViewById(R.id.txtPassword)

        dbHelper = ChampionsDBHelper(this)

        val sharedPreference = getSharedPreferences("config", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        if (sharedPreference.getBoolean("login", false)) {
            val intent = Intent(this, BottomNavigation::class.java)
            startActivity(intent)
        }


        // When the button is clicked, the user is logged in and taken to the BottomNavigation activity
        btn.setOnClickListener {
            Log.i("testCarlos", userText.getText().toString())
            Log.i("testCarlos", userPw.getText().toString())
            if (userText.text.toString() == "Admin" && userPw.text.toString() == "Admin") {
                editor.putString("user", "admin")
                editor.putBoolean("login", true)
                editor.apply()
                val intent = Intent(this, BottomNavigation::class.java)
                startActivity(intent)
            }
            userText.text.clear()
            userPw.text.clear()
        }
    }

    private fun setAppLocale(localeCode: String) {
        val resources: Resources = resources
        val dm: DisplayMetrics = resources.displayMetrics
        val config: Configuration = resources.configuration
        config.setLocale(Locale(localeCode.lowercase(Locale.getDefault())))

        resources.updateConfiguration(config, dm)
    }

    // onDestory() is called when the app is closed
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

    // dbHelper is used to access the database
    companion object {
        lateinit var dbHelper: ChampionsDBHelper
    }
}

