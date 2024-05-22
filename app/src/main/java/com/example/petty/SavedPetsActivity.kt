package com.example.petty

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SavedPetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_pets)

        val savedPets = intent.getParcelableArrayListExtra<Pet>("savedPets") ?: listOf()

        val backButton: Button = findViewById(R.id.backButton)
        val walkPetButton: Button = findViewById(R.id.walkPetButton)
        val adoptPetButton: Button = findViewById(R.id.adoptPetButton)
        val dogcatButton: Button = findViewById(R.id.dogcatButton)
        val savedPetsRecyclerView: RecyclerView = findViewById(R.id.savedPetsRecyclerView)

        savedPetsRecyclerView.layoutManager = LinearLayoutManager(this)
        savedPetsRecyclerView.adapter = PetAdapter(this, savedPets) { pet ->
            // Додайте логіку для обробки натискання на елемент списку, якщо потрібно
        }

        backButton.setOnClickListener {
            finish()
        }

        walkPetButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+380931934069")
            }
            startActivity(intent)
        }

        adoptPetButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+380969355915")
            }
            startActivity(intent)
        }

        dogcatButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://dogcat.com.ua"))
            startActivity(intent)
        }
    }
}
