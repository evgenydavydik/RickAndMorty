package com.davydikes.rickandmorty.repositories

import com.davydikes.rickandmorty.database.dao.CharacterDao
import com.davydikes.rickandmorty.models.Characters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RepositoryCharacters(
    private val charactersDao: CharacterDao
) {
    val charactersLocalFlow: Flow<List<Characters>> = charactersDao.getCharactersLiveFlow()

    suspend fun saveCharacters(characters: List<Characters>) {
        withContext(Dispatchers.IO) {
            charactersDao.saveCharacters(characters)
        }
    }

    suspend fun getCountCharacters(): Int {
        return charactersDao.getCountCharacter()
    }

    suspend fun deleteCharacters() {
        withContext(Dispatchers.IO) {
            charactersDao.deleteAllCharacters()
        }
    }
}