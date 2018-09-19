package com.example.blackjack

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.EditText
import android.provider.AlarmClock.EXTRA_MESSAGE





class MainActivity() : AppCompatActivity(){


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intro_screen.setOnClickListener {
            val intent = Intent(this,game::class.java)
            startActivity(intent)
        }
    }

}
