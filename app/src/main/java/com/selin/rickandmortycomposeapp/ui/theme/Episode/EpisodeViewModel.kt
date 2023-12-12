package com.selin.rickandmortycomposeapp.ui.theme.Episode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.repository.Repository
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    private val _list = MutableLiveData<List<EpisodeResponseList>>()
    val list: LiveData<List<EpisodeResponseList>> get() = _list

    suspend fun getEpisodesIds(ids: List<Int>): Response<EpisodeResponseList> {
        try {
            val response = repo.getEpisodesIds(ids)
            if (response.isSuccessful) {
                return response
            } else {
                throw IOException("Error getting character. HTTP ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun loadEpisodes() {
        viewModelScope.launch {
            _list.value = repo.getAllEpisodes()
        }
    }
}