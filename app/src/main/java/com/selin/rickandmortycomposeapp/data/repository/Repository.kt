package com.selin.rickandmortycomposeapp.data.repository

import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.LocationResponseList
import com.selin.rickandmortycomposeapp.data.room.DataSource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val dataSource: DataSource) {

    suspend fun getAllCharacters(): List<CharacterResponseList> {
        val firstPageResponse = dataSource.getCharacters()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allCharacters = mutableListOf<CharacterResponseList>()

            for (i in 1..totalPages) {
                val pageResponse = dataSource.getCharacters(i)
                if (pageResponse.isSuccessful) {
                    allCharacters.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }
            return allCharacters
        }
        return emptyList()
    }

    suspend fun getCharacterById(id: Int): Response<CharacterResponseList> {
        return dataSource.getCharacterById(id)
    }

    suspend fun getAllEpisodes(): List<EpisodeResponseList> {
        val firstPageResponse = dataSource.getEpisodes()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allEpisodes = mutableListOf<EpisodeResponseList>()

            for (i in 1..totalPages) {
                val pageResponse = dataSource.getEpisodes(i)
                if (pageResponse.isSuccessful) {
                    allEpisodes.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }
            return allEpisodes
        }
        return emptyList()
    }

    suspend fun getEpisodeById(id: Int): Response<EpisodeResponseList> {
        return dataSource.getEpisodeById(id)
    }

    suspend fun getEpisodesByIds(ids: List<Int>): Response<List<EpisodeResponseList>> {
        return dataSource.getEpisodesByIds(ids)
    }

    suspend fun getAllLocations(): List<LocationResponseList> {
        val firstPageResponse = dataSource.getLocations()

        if (firstPageResponse.isSuccessful) {
            val totalPages = firstPageResponse.body()?.info?.pages ?: 0
            val allLocations = mutableListOf<LocationResponseList>()

            for (i in 1..totalPages) {
                val pageResponse = dataSource.getLocations(i)
                if (pageResponse.isSuccessful) {
                    allLocations.addAll(pageResponse.body()?.results ?: emptyList())
                }
            }
            return allLocations
        }
        return emptyList()
    }

    suspend fun getLocationById(id: Int): Response<LocationResponseList> {
        return dataSource.getLocationById(id)
    }
}
