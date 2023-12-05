package com.selin.rickandmortycomposeapp.data.model.response

import com.selin.rickandmortycomposeapp.data.model.remote.Episode
import com.selin.rickandmortycomposeapp.data.model.remote.Info

data class EpisodeResponse(
    val info: Info,
    val results: List<Episode>
)