package com.selin.rickandmortycomposeapp.ui.theme.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.selin.rickandmortycomposeapp.data.repository.Repository
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _list = MutableLiveData<List<CharacterResponseList>>()
    val list: LiveData<List<CharacterResponseList>> get() = _list

    val paging = Pager(PagingConfig(pageSize = 20)) {
        CharacterPagingSource(repo)
    }.flow.cachedIn(viewModelScope)

    fun loadCharacters() {
        viewModelScope.launch {
            _list.value = repo.getAllCharacters()
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