package com.selin.rickandmortycomposeapp.data.repository

import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.LocationResponseList
import com.selin.rickandmortycomposeapp.data.remote.Service
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val api: Service) {

    suspend fun getAllCharacters(): List<CharacterResponseList> {
        val firstPageResponse = api.allCharacters()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allCharacters = mutableListOf<CharacterResponseList>()

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

    suspend fun getCharacterById(id: Int): CharacterResponseList {
        val response: Response<CharacterResponseList> = api.getCharacterById(id)
        return response.body()!!
    }


    // Episode
    suspend fun getAllEpisodes(): List<EpisodeResponseList> {
        val firstPageResponse = api.allEpisodes()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allEpisode = mutableListOf<EpisodeResponseList>()

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
    suspend fun getAllLocations(): List<LocationResponseList> {
        val firstPageResponse = api.allLocations()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allLocation = mutableListOf<LocationResponseList>()

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
