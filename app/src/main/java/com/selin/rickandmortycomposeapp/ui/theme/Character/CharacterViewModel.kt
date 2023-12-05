package com.selin.rickandmortycomposeapp.ui.theme.Character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.model.remote.Character
import com.selin.rickandmortycomposeapp.data.repository.RickAndMortyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val rickAndMortyRepo: RickAndMortyService) : ViewModel() {

    val list = MutableLiveData<List<Character>>()

    fun loadCharacters() {
        viewModelScope.launch {
            rickAndMortyRepo.getAllCharacters()
            list.value = rickAndMortyRepo.bringCharacters().value
        }
    }

    fun getCharacterById(id: Int): Character {
        viewModelScope.launch {
            rickAndMortyRepo.getCharacterById(id)
        }
        return list.value!![id]
    }
}