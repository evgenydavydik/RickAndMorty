package com.davydikes.rickandmorty.repositories

import com.davydikes.rickandmorty.api.ApiRickAndMorty
import com.davydikes.rickandmorty.models.Characters

class RepositoryApi(
    private val apiInterface: ApiRickAndMorty,
    private val charactersRepository: RepositoryCharacters
) {
    suspend fun importCharacters(): List<Characters> {
        val listCharacters: MutableList<Characters> =
            emptyList<Characters>().toMutableList()
        var response = apiInterface.getFirstPageCharacter()
        val apiCharacters = response.body()
        for (i in 1..apiCharacters!!.info.pages) {
            response = apiInterface.getNextPageCharacter(i)
            val characters = response.body()!!.result.map {
                Characters(
                    name = it.name,
                    image = it.image,
                    status = it.status,
                    species = it.species,
                    gender = it.gender
                )
            }
            listCharacters += characters
            if (charactersRepository.getCountCharacters() != apiCharacters.info.count) {
                charactersRepository.deleteCharacters()
                charactersRepository.saveCharacters(characters)
            }
        }
        return listCharacters
    }
}