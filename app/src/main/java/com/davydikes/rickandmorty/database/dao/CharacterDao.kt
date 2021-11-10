package com.davydikes.rickandmorty.database.dao

import androidx.room.*
import com.davydikes.rickandmorty.models.Characters
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveCharacters(characters: List<Characters>): List<Long>

    @Query("DELETE FROM characters")
    abstract fun deleteAllCharacters()

    @Query("SELECT * FROM characters ")
    abstract fun getCharactersLiveFlow(): Flow<List<Characters>>

    @Query("SELECT COUNT(name) FROM characters ")
    abstract fun getCountCharacter(): Int
}