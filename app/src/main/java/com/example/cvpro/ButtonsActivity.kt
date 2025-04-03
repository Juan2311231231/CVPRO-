package com.example.cvpro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ButtonsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        // Configurar iconos
        val chromeButton = findViewById<ImageView>(R.id.chromeButton)
        val linkedinButton = findViewById<ImageView>(R.id.linkedinButton)
        val githubButton = findViewById<ImageView>(R.id.githubButton)

        chromeButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/chrome"))
            startActivity(intent)
        }

        linkedinButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com"))
            startActivity(intent)
        }

        githubButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com"))
            startActivity(intent)
        }

        // Configurar navegación inferior
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_buttons -> {
                    // Ya estamos en ButtonsActivity
                    true
                }
                R.id.navigation_web -> {
                    val intent = Intent(this, WebActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_video -> {
                    val intent = Intent(this, VideoActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_photos -> {
                    val intent = Intent(this, PhotosActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.navigation_buttons
    }
} 