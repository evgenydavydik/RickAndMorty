package com.davydikes.rickandmorty.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters")

data class Characters(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String
) : Parcelable