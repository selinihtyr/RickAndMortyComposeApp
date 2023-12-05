package com.selin.rickandmortycomposeapp.data.model.response

import com.selin.rickandmortycomposeapp.data.model.remote.Info
import com.selin.rickandmortycomposeapp.data.model.remote.Location

data class LocationResponse(
    val info: Info,
    val results: List<Location>
)