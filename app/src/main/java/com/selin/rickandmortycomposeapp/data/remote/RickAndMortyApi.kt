package com.selin.rickandmortycomposeapp.data.remote

import com.selin.rickandmortycomposeapp.data.model.remote.Character
import com.selin.rickandmortycomposeapp.data.model.response.CharacterResponse
import com.selin.rickandmortycomposeapp.data.model.response.EpisodeResponse
import com.selin.rickandmortycomposeapp.data.model.response.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    suspend fun allCharacters(): Response<CharacterResponse>
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>
    @GET("episode")
    suspend fun allEpisodes(): Response<EpisodeResponse>

    @GET("location")
    suspend fun allLocations(): Response<LocationResponse>
}