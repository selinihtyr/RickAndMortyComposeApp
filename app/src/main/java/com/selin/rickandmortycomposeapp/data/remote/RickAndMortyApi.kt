package com.selin.rickandmortycomposeapp.data.remote

import com.selin.rickandmortycomposeapp.data.model.response.CharacterResponse
import com.selin.rickandmortycomposeapp.data.model.response.EpisodeResponse
import com.selin.rickandmortycomposeapp.data.model.response.LocationResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    suspend fun allCharacters(): Response<CharacterResponse>

    @GET("episode")
    suspend fun allEpisodes(): Response<EpisodeResponse>

    @GET("location")
    suspend fun allLocations(): Response<LocationResponse>
}