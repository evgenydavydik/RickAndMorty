package com.davydikes.rickandmorty.screen.main

import androidx.lifecycle.MutableLiveData
import com.davydikes.rickandmorty.repositories.RepositoryApi
import com.davydikes.rickandmorty.repositories.RepositoryCharacters
import com.davydikes.rickandmorty.support.CoroutineViewModel
import androidx.lifecycle.asLiveData
import com.davydikes.rickandmorty.models.Characters
import com.davydikes.rickandmorty.support.NetworkConnection
import kotlinx.coroutines.launch

class ViewModelMain(
    private val charactersRepository: RepositoryCharacters,
    private val rickAndMortyApiRepository: RepositoryApi,
    networkConnection: NetworkConnection
) : CoroutineViewModel() {
    var apiCharacterLiveData = MutableLiveData<List<Characters>>()
    var charactersLiveData = charactersRepository.charactersLocalFlow.asLiveData()
    val networkConnectionLiveData = networkConnection

    fun importCharacters() = launch {
        val result = rickAndMortyApiRepository.importCharacters()
        apiCharacterLiveData.postValue(result)
    }
}