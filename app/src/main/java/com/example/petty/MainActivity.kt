package com.example.petty

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var petRecyclerView: RecyclerView
    private lateinit var petAdapter: PetAdapter
    private lateinit var typeSpinner: Spinner
    private lateinit var distanceSpinner: Spinner
    private lateinit var genderSpinner: Spinner
    private lateinit var showSavedPetsButton: Button

    private val pets = listOf(
        Pet(name = "Monty", age = 3, distance = "5 km away", gender = "Male", size = "Small", personality = "Cute", imageUri = "cat1", description = "I will make you happy", type = "Cat", history = "Meet Monty. And his heart is completely open to new acquaintances \uD83E\uDD70 Monty was brought to the shelter from a nearby village, where he survived as best he could. Instead of luxurious white fur, they have huge mouths with an equally huge number of fleas and associated diseases. Monty was cured, neutered, trimmed, and now he is ready to go home. He is just an incredibly gentle little boy \uD83D\uDC3E There is so much unspent love in him that it is not difficult to imagine what kind of life he had. Therefore, he deserves only a loving and responsible family. Monty cannot be in the presence of dogs at all. Also, a family without other cats will be perfect for him. Pay attention to this snow-white handsome man. This is a treasure, not a cat ❤\uFE0F You can meet Monty in Kyiv! \n https://www.dogcat.com.ua/pet/monti"),
        Pet(name = "Florence", age = 1, distance = "8 km away", gender = "Female", size = "Small", personality = "Seriuos tail", imageUri = "cat2", description = "I will play with you", type = "Cat", history = "Florence got to the shelter during the evacuation from the city of Toretsk. She underwent treatment at the clinic and returned to Sirius again. She is a young and very beautiful cat. About Florence, we can say that this lady has a \"pepper\" \uD83D\uDE00 She does not like extra attention and various beauty procedures) But her big honey eyes cannot leave you indifferent) Call - we will be glad to introduce you to Florence✨\uD83D\uDC3E \n https://www.dogcat.com.ua/pet/florenciia"),
        Pet(name = "Don", age = 1, distance = "12 km away", gender = "Female", size = "Small", personality = "Mysterious", imageUri = "cat3", description = "I need your socialization", type = "Cat", history = "Dona is a cat evacuated from Toretsk (Donetsk region). This little girl had to go through a lot. Therefore, she is still getting used to new conditions. Next to a person, when he feels warmth and caress, he begins to adapt. We believe that her family will take care of her and will be ready to give her time to learn. To love, take care and protect her ❤\uFE0F \n https://www.dogcat.com.ua/pet/dona"),
        Pet(name = "Linda", age = 2, distance = "15 km away", gender = "Female", size = "Medium", personality = "Prodigy", imageUri = "dog1", description = "I love active leisure", type = "Dog", history = "Linda was a homebody. They lived in a large family, where besides her there were 6 tails. But one day they all ended up in Sirius because their Human is no longer with us. We got to know Linda better and we can proudly say that she is the best dog you could ever dream of\uD83D\uDCAB She needs a little time to open her ❤\uFE0F but then keep a tender hug. She will gladly spend time on a walk and open up like a peony in the spring) After the leisure time, she will not want to return to her enclosure at all and will not let you go with her deep gaze at all ... Linda is a very talented girl, she needs an active family that knows features of the breed. Do not miss the opportunity to fill your World with tender care!"),
        Pet(name = "Edward", age = 13, distance = "10 km away", gender = "Male", size = "Large", personality = "Friendly", imageUri = "dog2", description = "I will be your guard", type = "Dog", history = "Meet me, I'm Eduard, you can just call me Edik. I am a Moscow watchman. Going abroad, my family left me in the house they sold. The new owners of the house did not want to see me there and a decision was made by both sides to put me to sleep, because no one needs me anymore and I only disturb everyone, and the new owners are also afraid of me. I am 13 years old, but I still feel good. I have a good, friendly character. I have a disfigured back leg, it was not taken care of and it grew back as best it could. I also have ears bitten by flies, because I was not treated with anything. It's good that I didn't get sick, because I haven't been vaccinated for more than 2 years either. My life was supposed to end recently, because we had already agreed with the doctor, but a miracle happened at the last moment and Oksana Zhmur found out about it, she quickly contacted her friend, the owner of the Sirius shelter, and she ran to @lalatarapakina and called her \"12 Guardians\" \" they took me from the house where I was no longer needed (Dnipropetrovsk region) and handed me over to @Kharkiv - the wonderful team \"Rescue animals Kharkiv\", which quickly but carefully took me to the Sirius Shelter for homeless animals. I drove for a long time, spent the night with new friends, and finally, very tired, I arrived at my new home - Sirius' shelter. I was very surprised that I was hugged and kissed by people I did not know - shelter workers, because I was not nice to my people. Although I arrived late in the evening, I was greeted by the management of the shelter. I feel good in Sirius, there are even beautiful girls there, I was very happy to see a red-haired beauty who lives freely next to my enclosure, I learned that her name is Ryzhulya. Friends, they do not offend me, they caress me, walk with me, feed me deliciously, but I still really hope that my dream will come true, even at the end of my life, and I will meet my family, who will never betray me and I will finally i will be happy If you like me, call me!"),
        Pet(name = "Dusya", age = 9, distance = "20 km away", gender = "Female", size = "Small", personality = "Adult tail", imageUri = "dog3", description = "i will love you", type = "Dog", history = "I don't have a story because I've already forgotten everything... But you can create a new story with me!"),
        )

    private val savedPets = mutableListOf<Pet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        typeSpinner = findViewById(R.id.typeSpinner)
        distanceSpinner = findViewById(R.id.distanceSpinner)
        genderSpinner = findViewById(R.id.genderSpinner)
        showSavedPetsButton = findViewById(R.id.showSavedPetsButton)
        petRecyclerView = findViewById(R.id.petRecyclerView)

        petRecyclerView.layoutManager = LinearLayoutManager(this)
        petAdapter = PetAdapter(this, pets) { pet ->
            val intent = Intent(this, PetDetailActivity::class.java).apply {
                putExtra("petName", pet.name)
                putExtra("petPersonality", pet.personality)
                putExtra("petAge", pet.age)
                putExtra("petDistance", pet.distance)
                putExtra("petGender", pet.gender)
                putExtra("petSize", pet.size)
                putExtra("petDescription", pet.description)
                putExtra("petImageUri", pet.imageUri)
                putExtra("petType", pet.type)
                putExtra("petHistory", pet.history) // Передача історії
            }
            startActivityForResult(intent, REQUEST_CODE_SAVE_PET)
        }
        petRecyclerView.adapter = petAdapter

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filterPets()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        distanceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filterPets()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filterPets()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        showSavedPetsButton.setOnClickListener {
            val intent = Intent(this, SavedPetsActivity::class.java)
            intent.putParcelableArrayListExtra("savedPets", ArrayList(savedPets))
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SAVE_PET && resultCode == RESULT_OK) {
            val savedPet = data?.getParcelableExtra<Pet>("savedPet")
            savedPet?.let {
                savedPets.add(it)
            }
        }
    }

    private fun filterPets() {
        val type = typeSpinner.selectedItem.toString()
        val distance = distanceSpinner.selectedItem.toString()
        val gender = genderSpinner.selectedItem.toString()

        val filteredPets = pets.filter { pet ->
            (type == "All" || pet.type == type) &&
                    (distance == "All" || getDistanceValue(pet.distance) <= getDistanceValue(distance)) &&
                    (gender == "All" || pet.gender == gender)
        }
        petAdapter.updatePets(filteredPets)
    }

    private fun getDistanceValue(distance: String): Int {
        return when {
            distance.contains(" km away") -> {
                distance.replace(" km away", "").toInt()
            }
            distance == "Up to 10 km" -> 10
            distance == "Up to 15 km" -> 15
            distance == "Up to 20 km" -> 20
            distance == "Up to 50 km" -> 50
            else -> Int.MAX_VALUE
        }
    }

    companion object {
        private const val REQUEST_CODE_SAVE_PET = 1
    }
}
