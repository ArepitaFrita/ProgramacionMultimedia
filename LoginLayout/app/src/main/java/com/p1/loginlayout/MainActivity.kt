package com.p1.loginlayout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.p1.loginlayout.DB.ChampionsDBHelper

// Main Activity of the app
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btnSignIn)
        val userText: EditText = findViewById(R.id.txtUserName)
        val userPw: EditText = findViewById(R.id.txtPassword)

        val text: TextView = findViewById(R.id.textView)

        dbHelper = ChampionsDBHelper(this)





        // When the button is clicked, the user is logged in and taken to the BottomNavigation activity
        btn.setOnClickListener(){
            Log.i("testCarlos", userText.getText().toString())
            Log.i("testCarlos", userPw.getText().toString())
           /* if(userText.getText().toString() == "Admin" && userPw.getText().toString() == "Admin"){
                    text.setText(R.string.alreadyLogged)


            }*/
            val intent = Intent(this, BottomNavigation::class.java)
            startActivity(intent)
        }

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

