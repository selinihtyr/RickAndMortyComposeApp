package com.selin.rickandmortycomposeapp.ui.theme.Character

import androidx.lifecycle.LiveData
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
    private val service: RickAndMortyService
) : ViewModel() {

    private val _list = MutableLiveData<List<Character>>()
    val list: LiveData<List<Character>> get() = _list

    fun loadCharacters() {
        viewModelScope.launch {
            _list.value = service.getAllCharacters()
        }
    }


    suspend fun getCharacterById(id: Int): Character {
        viewModelScope.launch {
            service.getCharacterById(id)
        }
        return _list.value?.get(id) ?: throw NoSuchElementException("Character not found")
    }
}