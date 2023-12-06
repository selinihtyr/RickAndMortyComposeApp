package com.selin.rickandmortycomposeapp.ui.theme.Location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.model.remote.Location
import com.selin.rickandmortycomposeapp.data.repository.RickAndMortyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val service: RickAndMortyService
) : ViewModel() {
    val list = MutableLiveData<List<Location>>()

    fun loadLocations() {
        viewModelScope.launch {
            service.getAllLocations()
            service.locations.value?.let { list.value = it }
        }
    }
}