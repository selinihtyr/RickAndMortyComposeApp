package com.selin.rickandmortycomposeapp.data.remote

import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponse
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponse
import com.selin.rickandmortycomposeapp.data.retrofit.response.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("character")
    suspend fun allCharacters(): Response<CharacterResponse>

    @GET("character")
    suspend fun charactersByPage(@Query("page") page: Int): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterResponseList>

    @GET("episode")
    suspend fun allEpisodes(): Response<EpisodeResponse>

    @GET("episode")
    suspend fun episodesByPage(@Query("page") page: Int): Response<EpisodeResponse>

    @GET("location")
    suspend fun allLocations(): Response<LocationResponse>

    @GET("location")
    suspend fun locationsByPage(@Query("page") page: Int): Response<LocationResponse>
}