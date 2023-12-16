package com.selin.rickandmortycomposeapp.ui.theme.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selin.rickandmortycomposeapp.data.repository.Repository
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.LocationResponseList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private val _list = MutableLiveData<List<LocationResponseList>>()
    val list: LiveData<List<LocationResponseList>> get() = _list

    private val _location = MutableStateFlow<LocationResponseList?>(null)
    val location: StateFlow<LocationResponseList?> get() = _location

    fun loadLocations() {
        viewModelScope.launch {
            _list.value = repo.getAllLocations()
        }
    }

    suspend fun getLocationById(id: Int) {
        try {
            val response = repo.getLocationById(id)
            if (response.isSuccessful) {
                _location.value = response.body()
                Log.d("LocationViewModel", "getLocationById: ${response.body()}")
            } else {
                throw Exception("Error getting location. HTTP ${response.code()}")
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