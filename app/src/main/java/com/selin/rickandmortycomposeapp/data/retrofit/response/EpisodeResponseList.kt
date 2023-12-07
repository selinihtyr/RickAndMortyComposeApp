package com.selin.rickandmortycomposeapp.data.retrofit.response

data class EpisodeResponseList(
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)