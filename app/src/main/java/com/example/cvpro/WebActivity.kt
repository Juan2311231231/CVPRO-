package com.example.cvpro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class WebActivity : AppCompatActivity() {
    // Declaración de variables para las vistas
    private lateinit var urlEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        // Inicialización de las vistas
        urlEditText = findViewById(R.id.urlEditText)
        searchButton = findViewById(R.id.searchButton)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        // Configurar el botón de búsqueda
        searchButton.setOnClickListener {
            val url = urlEditText.text.toString()
            if (url.isNotEmpty()) {
                // Asegurarse de que la URL tenga el formato correcto
                val fullUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    "https://$url"
                } else {
                    url
                }
                // Abrir la URL en el navegador
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
                startActivity(intent)
            }
        }

        // Configurar la navegación inferior
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_buttons -> {
                    startActivity(Intent(this, ButtonsActivity::class.java))
                    true
                }
                R.id.navigation_web -> {
                    // Ya estamos en WebActivity
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
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.navigation_web
    }
} 