package com.selin.rickandmortycomposeapp.data.model.remote

data class Episode(
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)