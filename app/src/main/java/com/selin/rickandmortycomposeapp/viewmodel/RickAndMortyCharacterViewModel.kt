package com.selin.rickandmortycomposeapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.model.Character
import com.selin.rickandmortycomposeapp.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class RickAndMortyCharacterViewModel @Inject constructor(
    private val rickAndMortyRepo: RickAndMortyRepository) :
    ViewModel() {

    var characterList = MutableLiveData<List<Character>>()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            rickAndMortyRepo.getAllCharacters()
            rickAndMortyRepo.bringCharacters().observeForever {
                characterList.value = it
            }
        }
    }
}