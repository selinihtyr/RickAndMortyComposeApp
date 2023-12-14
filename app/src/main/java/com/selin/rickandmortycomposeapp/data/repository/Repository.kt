package com.selin.rickandmortycomposeapp.data.repository

import com.selin.rickandmortycomposeapp.data.remote.Service
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.LocationResponseList
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val service: Service) {

    suspend fun getAllCharacters(): List<CharacterResponseList> {
        val firstPageResponse = service.allCharacters()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allCharacters = mutableListOf<CharacterResponseList>()

            for (i in 1..totalPages) {
                val pageResponse = service.charactersByPage(i)
                if (pageResponse.isSuccessful) {
                    allCharacters.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }

            return allCharacters
        }
        return emptyList()
    }
    suspend fun getCharacterById(id: Int): Response<CharacterResponseList> {
        return service.getCharacterById(id)
    }
    // Episode
    suspend fun getAllEpisodes(): List<EpisodeResponseList> {
        val firstPageResponse = service.allEpisodes()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allEpisodes = mutableListOf<EpisodeResponseList>()

            for (i in 1..totalPages) {
                val pageResponse = service.episodeByPage(i)
                if (pageResponse.isSuccessful) {
                    allEpisodes.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }
            return allEpisodes
        }
        return emptyList()
    }
    suspend fun getEpisodeById(id: Int): Response<EpisodeResponseList> {
        return service.getEpisodeById(id)
    }
    suspend fun getEpisodesByIds(ids: List<Int>): Response<List<EpisodeResponseList>> {
        return service.getEpisodesByIds(ids)
    }
    // Location
    suspend fun getAllLocations(): List<LocationResponseList> {
        val firstPageResponse = service.allLocations()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allLocation = mutableListOf<LocationResponseList>()

            for (i in 1..totalPages) {
                val pageResponse = service.locationsByPage(i)
                if (pageResponse.isSuccessful) {
                    allLocation.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }
            return allLocation
        }
        return emptyList()
    }
}
