package com.selin.rickandmortycomposeapp.ui.theme.Location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.retrofit.response.LocationResponseList
import com.selin.rickandmortycomposeapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val service: Repository
) : ViewModel() {
    private val _list = MutableLiveData<List<LocationResponseList>>()
    val list: LiveData<List<LocationResponseList>> get() = _list

    fun loadLocations() {
        viewModelScope.launch {
            _list.value = service.getAllLocations()
        }
    }
}