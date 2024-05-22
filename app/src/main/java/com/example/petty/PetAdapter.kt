package com.example.petty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PetAdapter(private val context: Context, private var pets: List<Pet>, private val onItemClicked: (Pet) -> Unit) :
    RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(pets[position])
    }

    override fun getItemCount(): Int = pets.size

    inner class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val petImageView: ImageView = itemView.findViewById(R.id.petImageView)
        private val petNameTextView: TextView = itemView.findViewById(R.id.petNameTextView)
        private val petPersonalityTextView: TextView = itemView.findViewById(R.id.petPersonalityTextView)
        private val petAgeTextView: TextView = itemView.findViewById(R.id.petAgeTextView)
        private val petDistanceTextView: TextView = itemView.findViewById(R.id.petDistanceTextView)
        private val petGenderTextView: TextView = itemView.findViewById(R.id.petGenderTextView)
        private val petSizeTextView: TextView = itemView.findViewById(R.id.petSizeTextView)
        private val petDescriptionTextView: TextView = itemView.findViewById(R.id.petDescriptionTextView)

        fun bind(pet: Pet) {
            petNameTextView.text = pet.name
            petPersonalityTextView.text = pet.personality
            petAgeTextView.text = "Age: ${pet.age}"
            petDistanceTextView.text = "Distance: ${pet.distance}"
            petGenderTextView.text = "Gender: ${pet.gender}"
            petSizeTextView.text = "Size: ${pet.size}"
            petDescriptionTextView.text = "Description: ${pet.description}"

            val imageResource = itemView.context.resources.getIdentifier(pet.imageUri, "drawable", itemView.context.packageName)
            Glide.with(itemView.context)
                .load(imageResource)
                .into(petImageView)

            itemView.setOnClickListener {
                onItemClicked(pet)
            }
        }
    }

    fun updatePets(newPets: List<Pet>) {
        pets = newPets
        notifyDataSetChanged()
    }

}
