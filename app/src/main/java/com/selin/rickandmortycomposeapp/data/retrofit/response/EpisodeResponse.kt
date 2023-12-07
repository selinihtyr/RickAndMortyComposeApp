package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.selin.rickandmortycomposeapp.data.retrofit.model.Info

data class EpisodeResponse(
    val info: Info,
    val results: List<EpisodeResponseList>
)