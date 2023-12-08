package com.selin.rickandmortycomposeapp.ui.theme.Character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val service: Repository
) : ViewModel() {

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _list = MutableLiveData<List<CharacterResponseList>>()
    val list: LiveData<List<CharacterResponseList>> get() = _list

    fun loadCharacters() {
        viewModelScope.launch {
            try {
                _loadingState.value = true
                _list.value = service.getAllCharacters()
            } finally {
                _loadingState.value = false
            }
        }
    }

    fun getCharacterById(id: Int): CharacterResponseList {
        viewModelScope.launch {
            service.getCharacterById(id)
        }
        return _list.value?.get(id) ?: throw NoSuchElementException("Character not found")
    }
}