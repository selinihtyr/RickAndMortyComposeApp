package com.selin.rickandmortycomposeapp.entity.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)