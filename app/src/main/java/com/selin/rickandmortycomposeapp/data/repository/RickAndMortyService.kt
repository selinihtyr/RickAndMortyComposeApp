package com.selin.rickandmortycomposeapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.selin.rickandmortycomposeapp.data.model.remote.Character
import com.selin.rickandmortycomposeapp.data.model.remote.Episode
import com.selin.rickandmortycomposeapp.data.model.remote.Location
import com.selin.rickandmortycomposeapp.data.model.response.CharacterResponse
import com.selin.rickandmortycomposeapp.data.model.response.EpisodeResponse
import com.selin.rickandmortycomposeapp.data.model.response.LocationResponse
import com.selin.rickandmortycomposeapp.data.remote.RickAndMortyApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyService @Inject constructor(private val rickAndMortyApi: RickAndMortyApi) {

    private val characterList = MutableLiveData<List<Character>>()
    private val characters: LiveData<List<Character>> get() = characterList

    private val episodeList = MutableLiveData<List<Episode>>()
    private val episodes: LiveData<List<Episode>> get() = episodeList

    private val locationList = MutableLiveData<List<Location>>()
    private val locations: LiveData<List<Location>> get() = locationList

    //Character
    fun bringCharacters(): LiveData<List<Character>> {
        return characters
    }
    suspend fun getAllCharacters() {
        val response: Response<CharacterResponse> = rickAndMortyApi.allCharacters()
        if (response.isSuccessful) {
            characterList.value = response.body()?.results
        }
    }
    suspend fun getCharacterById(id: Int): Character {
        val response: Response<Character> = rickAndMortyApi.getCharacterById(id)
        return response.body()!!
    }


    //Episode
    fun bringEpisodes(): LiveData<List<Episode>> {
        return episodes
    }
    suspend fun getAllEpisodes() {
        val response: Response<EpisodeResponse> = rickAndMortyApi.allEpisodes()
        if (response.isSuccessful) {
            episodeList.value = response.body()?.results
        }
    }


    //Location
    fun bringLocations(): LiveData<List<Location>> {
        return locations
    }

    suspend fun getAllLocations() {
        val response: Response<LocationResponse> = rickAndMortyApi.allLocations()
        if (response.isSuccessful) {
            locationList.value = response.body()?.results
        }
    }
}
