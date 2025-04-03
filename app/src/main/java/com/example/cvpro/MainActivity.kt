package com.example.cvpro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.navigation_profile

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_buttons -> {
                    startActivity(Intent(this, ButtonsActivity::class.java))
                    true
                }
                R.id.navigation_web -> {
                    startActivity(Intent(this, WebActivity::class.java))
                    true
                }
                R.id.navigation_video -> {
                    startActivity(Intent(this, VideoActivity::class.java))
                    true
                }
                R.id.navigation_photos -> {
                    startActivity(Intent(this, PhotosActivity::class.java))
                    true
                }
                R.id.navigation_profile -> true
                else -> false
            }
        }
    }
}