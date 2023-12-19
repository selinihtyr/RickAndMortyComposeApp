package com.selin.rickandmortycomposeapp.data.room

import com.selin.rickandmortycomposeapp.data.remote.Service

class DataSource(private val service: Service) {
        suspend fun getCharacters() = service.getCharacters()
        suspend fun getCharacterById(id: Int) = service.getCharacterById(id)

        suspend fun getEpisodes() = service.getEpisodes()
        suspend fun getEpisodeById(id: Int) = service.getEpisodeById(id)
        suspend fun getEpisodesByIds(ids: List<Int>) = service.getEpisodesByIds(ids)

        suspend fun getLocations() = service.getLocations()
        suspend fun getLocationById(id: Int) = service.getLocationById(id)
}