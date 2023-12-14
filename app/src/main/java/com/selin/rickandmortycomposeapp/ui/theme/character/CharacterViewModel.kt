package com.selin.rickandmortycomposeapp.ui.theme.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _list = MutableLiveData<List<CharacterResponseList>>()
    val list: LiveData<List<CharacterResponseList>> get() = _list

    val characters = Pager(PagingConfig(pageSize = 20)) {
        CharacterPagingSource(repo)
    }.flow.cachedIn(viewModelScope)

    fun loadCharacters() {
        viewModelScope.launch {
            try {
                _loadingState.value = true
                _list.value = repo.getAllCharacters()
            } finally {
                _loadingState.value = false
            }
        }
    }

    suspend fun getCharacterById(id: Int): CharacterResponseList {
        try {
            val response = repo.getCharacterById(id)
            if (response.isSuccessful) {
                return response.body() ?: throw NoSuchElementException("Character not found")
            } else {
                throw IOException("Error getting character. HTTP ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

}