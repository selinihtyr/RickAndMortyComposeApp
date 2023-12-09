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

    suspend fun getEpisodesById(episodeId: Int): Response<EpisodeResponseList> {
        try {
            val response = repo.getEpisodesById(episodeId)
            if (response.isSuccessful) {
                return response
            } else {
                Log.e("API_ERROR", "Error getting episode. HTTP ${response.code()}")
                // Handle the error gracefully
                return Response.error(response.code(), response.errorBody()!!)
            }
        } catch (e: Exception) {
            Log.e("API_ERROR", "Exception: ${e.message}")
            // Handle the error gracefully
            return Response.error(500, "Internal Server Error".toResponseBody())
        }
    }

    fun loadEpisodes() {
        viewModelScope.launch {
            _list.value = repo.getAllEpisodes()
        }
    }
}