package com.selin.rickandmortycomposeapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.selin.rickandmortycomposeapp.data.model.remote.Character
import com.selin.rickandmortycomposeapp.data.model.response.CharacterResponse
import com.selin.rickandmortycomposeapp.data.remote.RickAndMortyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyRepository @Inject constructor(private val rickAndMortyApi: RickAndMortyApi) {

    private val characterList = MutableLiveData<List<Character>>()

    fun bringCharacters(): MutableLiveData<List<Character>> {
        return characterList
    }

    suspend fun getAllCharacters() {
        rickAndMortyApi.allCharacters().enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    characterList.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }
}
