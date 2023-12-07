package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.selin.rickandmortycomposeapp.data.retrofit.model.CharacterLocation
import com.selin.rickandmortycomposeapp.data.retrofit.model.Origin

data class CharacterResponseList(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterLocation,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)