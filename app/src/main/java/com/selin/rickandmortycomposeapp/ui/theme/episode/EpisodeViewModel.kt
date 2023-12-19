package com.selin.rickandmortycomposeapp.ui.theme.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.repository.Repository
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private val _list = MutableLiveData<List<EpisodeResponseList>>()
    val list: LiveData<List<EpisodeResponseList>> get() = _list

    private val _episode = MutableStateFlow<EpisodeResponseList?>(null)
    val episode: StateFlow<EpisodeResponseList?> get() = _episode

    fun loadEpisodes() {
        viewModelScope.launch {
            _list.value = repo.getAllEpisodes()
        }
    }

    suspend fun getEpisodeById(id: Int) {
        try {
            val response = repo.getEpisodeById(id)
            if (response.isSuccessful) {
                _episode.value = response.body()
            } else {
                throw IOException("Error getting episode. HTTP ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getEpisodesIds(ids: List<Int>): Response<List<EpisodeResponseList>> {
        try {
            val response = repo.getEpisodesByIds(ids)
            if (response.isSuccessful) {
                return response
            } else {
                throw IOException("Error getting character. HTTP ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
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