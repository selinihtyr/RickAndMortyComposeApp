package com.selin.rickandmortycomposeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selin.rickandmortycomposeapp.entity.character.Character
import com.selin.rickandmortycomposeapp.repository.RickAndMortyRepository

class RickAndMortyHomePageViewModel: ViewModel() {
    var rickAndMortyRepo = RickAndMortyRepository()
    var characterList = MutableLiveData<List<Character>>()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        rickAndMortyRepo.getAllCharacters()
        characterList = rickAndMortyRepo.bringCharacters()

    }
}