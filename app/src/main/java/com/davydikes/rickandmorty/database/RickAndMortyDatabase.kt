package com.davydikes.rickandmorty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davydikes.rickandmorty.database.dao.CharacterDao
import com.davydikes.rickandmorty.models.Characters

@Database(
    entities = [
        Characters::class
    ],
    version = 1,
    exportSchema = false
)

abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
}

object DatabaseConstructor {
    fun create(context: Context): RickAndMortyDatabase =
        Room.databaseBuilder(
            context,
            RickAndMortyDatabase::class.java,
            "com.davydikes.rickandmorty.db"
        ).build()
}