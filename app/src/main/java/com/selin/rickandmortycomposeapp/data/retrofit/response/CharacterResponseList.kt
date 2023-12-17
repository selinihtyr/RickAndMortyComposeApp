package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.google.gson.annotations.SerializedName
import com.selin.rickandmortycomposeapp.data.retrofit.model.CharacterLocation
import com.selin.rickandmortycomposeapp.data.retrofit.model.Origin


data class CharacterResponseList(
    @SerializedName("created") val created: String,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("gender") val gender: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("location") val location: CharacterLocation,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: Origin,
    @SerializedName("species") val species: String,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)