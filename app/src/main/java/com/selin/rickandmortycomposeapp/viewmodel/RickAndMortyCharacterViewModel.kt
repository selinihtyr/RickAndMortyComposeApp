package com.selin.rickandmortycomposeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selin.rickandmortycomposeapp.data.model.Character
import com.selin.rickandmortycomposeapp.repository.RickAndMortyRepository
import javax.inject.Inject

class RickAndMortyCharacterViewModel @Inject constructor(private val rickAndMortyRepo: RickAndMortyRepository) :
    ViewModel() {

    var characterList = MutableLiveData<List<Character>>()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        rickAndMortyRepo.getAllCharacters()
        characterList = rickAndMortyRepo.bringCharacters()

    }
}