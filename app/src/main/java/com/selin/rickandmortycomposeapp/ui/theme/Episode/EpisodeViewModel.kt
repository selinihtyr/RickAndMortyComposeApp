package com.selin.rickandmortycomposeapp.ui.theme.Episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.model.remote.Episode
import com.selin.rickandmortycomposeapp.data.repository.RickAndMortyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val service: RickAndMortyService) : ViewModel() {

    val list = MutableLiveData<List<Episode>>()

    fun loadEpisodes() {
        viewModelScope.launch {
            service.getAllEpisodes()
            service.episodes.value?.let { list.value = it }
        }
    }

}