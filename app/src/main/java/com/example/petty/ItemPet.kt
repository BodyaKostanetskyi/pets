package com.example.petty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat.Type.systemBars

class ItemPet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_pet)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.petImageView)) { v, insets ->
            val systemBars = insets.getInsets(systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
