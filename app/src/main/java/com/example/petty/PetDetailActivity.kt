package com.example.petty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class PetDetailActivity : AppCompatActivity() {

    private lateinit var petNameTextView: TextView
    private lateinit var petPersonalityTextView: TextView
    private lateinit var petAgeTextView: TextView
    private lateinit var petDistanceTextView: TextView
    private lateinit var petGenderTextView: TextView
    private lateinit var petSizeTextView: TextView
    private lateinit var petDescriptionTextView: TextView
    private lateinit var petImageView: ImageView
    private lateinit var petHistoryTextView: TextView // Новий TextView для історії
    private lateinit var savePetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_detail)

        val backButton: Button = findViewById(R.id.backButton)
        petNameTextView = findViewById(R.id.petNameTextView)
        petPersonalityTextView = findViewById(R.id.petPersonalityTextView)
        petAgeTextView = findViewById(R.id.petAgeTextView)
        petDistanceTextView = findViewById(R.id.petDistanceTextView)
        petGenderTextView = findViewById(R.id.petGenderTextView)
        petSizeTextView = findViewById(R.id.petSizeTextView)
        petDescriptionTextView = findViewById(R.id.petDescriptionTextView)
        petImageView = findViewById(R.id.petImageView)
        petHistoryTextView = findViewById(R.id.petHistoryTextView) // Ініціалізація нового TextView
        savePetButton = findViewById(R.id.savePetButton)

        val petName = intent.getStringExtra("petName") ?: ""
        val petPersonality = intent.getStringExtra("petPersonality") ?: ""
        val petAge = intent.getIntExtra("petAge", 0)
        val petDistance = intent.getStringExtra("petDistance") ?: ""
        val petGender = intent.getStringExtra("petGender") ?: ""
        val petSize = intent.getStringExtra("petSize") ?: ""
        val petDescription = intent.getStringExtra("petDescription") ?: ""
        val petImageUri = intent.getStringExtra("petImageUri") ?: ""
        val petType = intent.getStringExtra("petType") ?: ""
        val petHistory = intent.getStringExtra("petHistory") ?: ""

        petNameTextView.text = petName
        petPersonalityTextView.text = petPersonality
        petAgeTextView.text = "Age: $petAge"
        petDistanceTextView.text = "Distance: $petDistance"
        petGenderTextView.text = "Gender: $petGender"
        petSizeTextView.text = "Size: $petSize"
        petDescriptionTextView.text = "Description: $petDescription"
        petHistoryTextView.text = petHistory

        val imageResource = resources.getIdentifier(petImageUri, "drawable", packageName)
        Glide.with(this)
            .load(imageResource)
            .into(petImageView)

        savePetButton.setOnClickListener {
            val intent = Intent().apply {
                putExtra("savedPet", Pet(petName, petAge, petDistance, petGender, petSize, petPersonality, petImageUri, petDescription, petType, petHistory))
            }
            setResult(RESULT_OK, intent)
            finish()
        }
        backButton.setOnClickListener {
            finish()
        }
    }
}
