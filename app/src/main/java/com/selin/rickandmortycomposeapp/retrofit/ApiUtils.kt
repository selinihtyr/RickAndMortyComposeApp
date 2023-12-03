package com.selin.rickandmortycomposeapp.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "https://rickandmortyapi.com/api/"

        fun getRickAndMortyApi():RickAndMortyApi{
            return RetrofitClient.getClient(BASE_URL).create(RickAndMortyApi::class.java)
        }
    }
}