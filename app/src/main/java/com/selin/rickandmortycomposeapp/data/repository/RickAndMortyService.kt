package com.selin.rickandmortycomposeapp.data.repository

import com.selin.rickandmortycomposeapp.data.model.remote.Character
import com.selin.rickandmortycomposeapp.data.model.remote.Episode
import com.selin.rickandmortycomposeapp.data.model.remote.Location
import com.selin.rickandmortycomposeapp.data.remote.RickAndMortyApi
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
    suspend fun getAllEpisodes(): List<Episode> {
        val firstPageResponse = api.allEpisodes()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allEpisode = mutableListOf<Episode>()

            for (i in 1..totalPages) {
                val pageResponse = api.episodesByPage(i)
                if (pageResponse.isSuccessful) {
                    allEpisode.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }

            return allEpisode
        }
        return emptyList()
    }

    // Location
    suspend fun getAllLocations(): List<Location> {
        val firstPageResponse = api.allLocations()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allLocation = mutableListOf<Location>()

            for (i in 1..totalPages) {
                val pageResponse = api.locationsByPage(i)
                if (pageResponse.isSuccessful) {
                    allLocation.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }
            return allLocation
        }
        return emptyList()
    }
}
