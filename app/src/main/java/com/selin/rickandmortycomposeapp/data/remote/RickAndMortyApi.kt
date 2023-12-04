package com.selin.rickandmortycomposeapp.data.remote

import com.selin.rickandmortycomposeapp.data.model.response.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    fun allCharacters(): Call<CharacterResponse>
}