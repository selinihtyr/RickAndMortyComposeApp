package com.selin.rickandmortycomposeapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.selin.rickandmortycomposeapp.entity.character.CharacterResponse
import com.selin.rickandmortycomposeapp.entity.character.Character
import com.selin.rickandmortycomposeapp.retrofit.ApiUtils
import com.selin.rickandmortycomposeapp.retrofit.RickAndMortyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RickAndMortyRepository {
    private var rickAndMortyApi: RickAndMortyApi = ApiUtils.getRickAndMortyApi()
    val characterList = MutableLiveData<List<Character>>()

    fun bringCharacters(): MutableLiveData<List<Character>> {
        return characterList
    }

    fun getAllCharacters() {
        rickAndMortyApi.allCharacters().enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                val characters = response.body()?.results
                characterList.value = characters
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("API Error", "API call failed", t)
            }
        })
    }
}
