package com.selin.rickandmortycomposeapp.data.model.response

import com.selin.rickandmortycomposeapp.data.model.remote.Character
import com.selin.rickandmortycomposeapp.data.model.remote.Info

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)