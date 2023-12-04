package com.selin.rickandmortycomposeapp.data.model.response

import com.selin.rickandmortycomposeapp.data.model.Character
import com.selin.rickandmortycomposeapp.data.model.Info

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)