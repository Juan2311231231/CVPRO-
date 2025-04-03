package com.example.cvpro

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class VideoActivity : AppCompatActivity() {
    // Declaración de variables para las vistas
    private lateinit var videoView: VideoView
    private lateinit var selectButton: Button
    private lateinit var playButton: ImageButton
    private lateinit var pauseButton: ImageButton
    private lateinit var stopButton: ImageButton
    private lateinit var bottomNavigation: BottomNavigationView

    companion object {
        private const val PICK_VIDEO_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        // Inicialización de las vistas
        videoView = findViewById(R.id.videoView)
        selectButton = findViewById(R.id.selectButton)
        playButton = findViewById(R.id.playButton)
        pauseButton = findViewById(R.id.pauseButton)
        stopButton = findViewById(R.id.stopButton)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        // Configurar botones de control
        selectButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "video/*"
            startActivityForResult(intent, PICK_VIDEO_REQUEST)
        }

        playButton.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }

        pauseButton.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        stopButton.setOnClickListener {
            videoView.stopPlayback()
            videoView.resume()
        }

        // Configurar la navegación inferior
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
                    // Ya estamos en VideoActivity
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
        bottomNavigation.selectedItemId = R.id.navigation_video
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                videoView.setVideoURI(uri)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // Pausar el video cuando la actividad no está visible
        if (videoView.isPlaying) {
            videoView.pause()
        }
    }
} 