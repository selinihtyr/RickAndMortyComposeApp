package com.selin.rickandmortycomposeapp.ui.theme.Episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import com.selin.rickandmortycomposeapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val service: Repository
) : ViewModel() {

    private val _list = MutableLiveData<List<EpisodeResponseList>>()
    val list: LiveData<List<EpisodeResponseList>> get() = _list

    fun loadEpisodes() {
        viewModelScope.launch {
            _list.value = service.getAllEpisodes()
        }
    }
}