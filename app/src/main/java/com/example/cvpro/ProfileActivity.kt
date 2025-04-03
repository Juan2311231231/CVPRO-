package com.example.cvpro

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileImage: CircleImageView
    private lateinit var nameText: TextView
    private lateinit var bioText: TextView
    private lateinit var emailText: TextView
    private lateinit var linkedinButton: ImageButton
    private lateinit var instagramButton: ImageButton
    private lateinit var githubButton: ImageButton
    private lateinit var bottomNavigation: BottomNavigationView

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inicialización de las vistas
        profileImage = findViewById(R.id.profileImage)
        nameText = findViewById(R.id.nameText)
        bioText = findViewById(R.id.bioText)
        emailText = findViewById(R.id.emailText)
        linkedinButton = findViewById(R.id.linkedinButton)
        instagramButton = findViewById(R.id.instagramButton)
        githubButton = findViewById(R.id.githubButton)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        // Configurar click en la imagen de perfil
        profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        // Configurar botones de redes sociales
        linkedinButton.setOnClickListener {
            openUrl("https://www.linkedin.com/in/alex-rodriguez")
        }

        instagramButton.setOnClickListener {
            openUrl("https://www.instagram.com/alex.rodriguez")
        }

        githubButton.setOnClickListener {
            openUrl("https://github.com/alex-rodriguez")
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
                    startActivity(Intent(this, VideoActivity::class.java))
                    true
                }
                R.id.navigation_photos -> {
                    startActivity(Intent(this, PhotosActivity::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    // Ya estamos en ProfileActivity
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.navigation_profile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                profileImage.setImageURI(uri)
            }
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
} 