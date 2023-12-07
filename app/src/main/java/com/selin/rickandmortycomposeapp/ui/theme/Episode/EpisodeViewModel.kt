package com.selin.rickandmortycomposeapp.ui.theme.Episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.model.remote.Episode
import com.selin.rickandmortycomposeapp.data.repository.RickAndMortyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val service: RickAndMortyService
) : ViewModel() {

    private val _list = MutableLiveData<List<Episode>>()
    val list: LiveData<List<Episode>> get() = _list

    fun loadEpisodes() {
        viewModelScope.launch {
            _list.value = service.getAllEpisodes()
        }
    }
}