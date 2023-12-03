package com.selin.rickandmortycomposeapp.retrofit

import com.selin.rickandmortycomposeapp.entity.character.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    fun allCharacters(): Call<CharacterResponse>
}