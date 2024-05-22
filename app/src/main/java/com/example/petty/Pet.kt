package com.example.petty

import android.os.Parcel
import android.os.Parcelable

data class Pet(
    val name: String,
    val age: Int,
    val distance: String,
    val gender: String,
    val size: String,
    val personality: String,
    val imageUri: String,
    val description: String,
    val type: String,
    val history: String // Нове поле для історії
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "" // Читання історії з Parcel
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(distance)
        parcel.writeString(gender)
        parcel.writeString(size)
        parcel.writeString(personality)
        parcel.writeString(imageUri)
        parcel.writeString(description)
        parcel.writeString(type)
        parcel.writeString(history) // Запис історії до Parcel
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pet> {
        override fun createFromParcel(parcel: Parcel): Pet {
            return Pet(parcel)
        }

        override fun newArray(size: Int): Array<Pet?> {
            return arrayOfNulls(size)
        }
    }
}
