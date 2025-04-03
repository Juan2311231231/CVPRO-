package com.example.cvpro

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PhotosActivity : AppCompatActivity() {
    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var mainImageView: ImageView
    private lateinit var imageDescription: TextView
    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var addPhotoButton: Button
    private lateinit var bottomNavigation: BottomNavigationView

    private val photos = mutableListOf<Photo>()
    private var currentPhotoIndex = 0

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    data class Photo(
        val uri: Uri,
        val description: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        // Inicializar vistas
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
        mainImageView = findViewById(R.id.mainImageView)
        imageDescription = findViewById(R.id.imageDescription)
        prevButton = findViewById(R.id.prevButton)
        nextButton = findViewById(R.id.nextButton)
        addPhotoButton = findViewById(R.id.addPhotoButton)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        // Configurar botones de navegación
        prevButton.setOnClickListener {
            if (photos.isNotEmpty()) {
                currentPhotoIndex = (currentPhotoIndex - 1 + photos.size) % photos.size
                updateMainPhoto()
            }
        }

        nextButton.setOnClickListener {
            if (photos.isNotEmpty()) {
                currentPhotoIndex = (currentPhotoIndex + 1) % photos.size
                updateMainPhoto()
            }
        }

        // Configurar botón de añadir foto
        addPhotoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        // Configurar navegación inferior
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
                    // Ya estamos en PhotosActivity
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
        bottomNavigation.selectedItemId = R.id.navigation_photos
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                showDescriptionDialog(uri)
            }
        }
    }

    private fun showDescriptionDialog(imageUri: Uri) {
        val editText = EditText(this)
        editText.hint = "Ingrese una descripción para la imagen"

        AlertDialog.Builder(this)
            .setTitle("Añadir descripción")
            .setView(editText)
            .setPositiveButton("Guardar") { _, _ ->
                val description = editText.text.toString()
                addPhoto(Photo(imageUri, description))
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun addPhoto(photo: Photo) {
        photos.add(photo)
        currentPhotoIndex = photos.size - 1
        updatePhotosGrid()
        updateMainPhoto()
    }

    private fun updatePhotosGrid() {
        val gridViews = listOf(imageView1, imageView2, imageView3)
        for (i in gridViews.indices) {
            if (i < photos.size) {
                gridViews[i].setImageURI(photos[i].uri)
            } else {
                gridViews[i].setImageURI(null)
            }
        }
    }

    private fun updateMainPhoto() {
        if (photos.isNotEmpty()) {
            val photo = photos[currentPhotoIndex]
            mainImageView.setImageURI(photo.uri)
            imageDescription.text = photo.description
        }
    }
} 