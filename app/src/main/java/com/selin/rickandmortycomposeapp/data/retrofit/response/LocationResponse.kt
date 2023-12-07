package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.selin.rickandmortycomposeapp.data.retrofit.model.Info

data class LocationResponse(
    val info: Info,
    val results: List<LocationResponseList>
)