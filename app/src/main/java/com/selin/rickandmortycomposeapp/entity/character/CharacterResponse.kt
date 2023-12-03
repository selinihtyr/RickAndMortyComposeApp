package com.selin.rickandmortycomposeapp.entity.character

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)