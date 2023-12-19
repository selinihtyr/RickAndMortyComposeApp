package com.selin.rickandmortycomposeapp.data.room

import com.selin.rickandmortycomposeapp.data.remote.Service

class DataSource(private val service: Service) {
        suspend fun getCharacters(page: Int? = null) = service.getCharacters(page)
        suspend fun getCharacterById(id: Int) = service.getCharacterById(id)

        suspend fun getEpisodes(page: Int? = null) = service.getEpisodes(page)
        suspend fun getEpisodeById(id: Int) = service.getEpisodeById(id)
        suspend fun getEpisodesByIds(ids: List<Int>) = service.getEpisodesByIds(ids)

        suspend fun getLocations(page: Int? = null) = service.getLocations(page)
        suspend fun getLocationById(id: Int) = service.getLocationById(id)
}