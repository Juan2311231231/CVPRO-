package com.example.cvpro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Ocultar la barra de acción si está presente
        supportActionBar?.hide()

        // Esperar 2 segundos antes de pasar a la actividad principal
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }, 2000) // 2000 ms = 2 segundos
    }
} 