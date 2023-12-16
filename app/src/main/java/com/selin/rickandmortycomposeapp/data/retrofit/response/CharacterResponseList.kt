package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.google.gson.annotations.SerializedName
import com.selin.rickandmortycomposeapp.data.retrofit.model.CharacterLocation
import com.selin.rickandmortycomposeapp.data.retrofit.model.Origin


data class CharacterResponseList(
    @SerializedName("created") var created: String,
    @SerializedName("episode") var episode: List<String>,
    @SerializedName("gender") var gender: String,
    @SerializedName("id") var id: Int,
    @SerializedName("image") var image: String,
    @SerializedName("location") var location: CharacterLocation,
    @SerializedName("name") var name: String,
    @SerializedName("origin") var origin: Origin,
    @SerializedName("species") var species: String,
    @SerializedName("status") var status: String,
    @SerializedName("type") var type: String,
    @SerializedName("url") var url: String
)