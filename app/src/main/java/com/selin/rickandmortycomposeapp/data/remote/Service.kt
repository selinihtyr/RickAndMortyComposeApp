package com.selin.rickandmortycomposeapp.data.remote

import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponse
import com.selin.rickandmortycomposeapp.data.retrofit.response.CharacterResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponse
import com.selin.rickandmortycomposeapp.data.retrofit.response.EpisodeResponseList
import com.selin.rickandmortycomposeapp.data.retrofit.response.LocationResponse
import com.selin.rickandmortycomposeapp.data.retrofit.response.LocationResponseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int? = null): Response<CharacterResponse>
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterResponseList>

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int? = null): Response<EpisodeResponse>
    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Response<EpisodeResponseList>
    @GET("episode/{ids}")
    suspend fun getEpisodesByIds(@Path("ids") characterId: List<Int>): Response<List<EpisodeResponseList>>

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int? = null): Response<LocationResponse>
    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): Response<LocationResponseList>
}