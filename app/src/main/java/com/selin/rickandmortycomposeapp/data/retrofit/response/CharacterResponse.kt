package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.selin.rickandmortycomposeapp.data.retrofit.model.Info

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterResponseList>
)