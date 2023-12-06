package com.selin.rickandmortycomposeapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.selin.rickandmortycomposeapp.data.model.remote.Character
import com.selin.rickandmortycomposeapp.data.model.remote.Episode
import com.selin.rickandmortycomposeapp.data.model.remote.Location
import com.selin.rickandmortycomposeapp.data.model.response.CharacterResponse
import com.selin.rickandmortycomposeapp.data.model.response.EpisodeResponse
import com.selin.rickandmortycomposeapp.data.model.response.LocationResponse
import com.selin.rickandmortycomposeapp.data.remote.RickAndMortyApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyService @Inject constructor(private val api: RickAndMortyApi) {

    suspend fun getAllCharacters(): List<Character> {
        val firstPageResponse = api.allCharacters()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allCharacters = mutableListOf<Character>()

            for (i in 1..totalPages) {
                val pageResponse = api.charactersByPage(i)
                if (pageResponse.isSuccessful) {
                    allCharacters.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }

            return allCharacters
        }
        return emptyList()
    }



    suspend fun getCharacterById(id: Int): Character {
        val response: Response<Character> = api.getCharacterById(id)
        return response.body()!!
    }


    // Episode
    private val episodeList = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> get() = episodeList

    suspend fun getAllEpisodes() {
        val response: Response<EpisodeResponse> = api.allEpisodes()
        if (response.isSuccessful) {
            episodeList.value = response.body()?.results
        }
    }

    // Location
    private val locationList = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> get() = locationList

    suspend fun getAllLocations() {
        val response: Response<LocationResponse> = api.allLocations()
        if (response.isSuccessful) {
            locationList.value = response.body()?.results
        }
    }
}
