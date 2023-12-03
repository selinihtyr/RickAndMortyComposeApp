package com.selin.rickandmortycomposeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selin.rickandmortycomposeapp.entity.character.Character
import com.selin.rickandmortycomposeapp.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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