package com.selin.rickandmortycomposeapp.data.retrofit.response

data class LocationResponseList(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)