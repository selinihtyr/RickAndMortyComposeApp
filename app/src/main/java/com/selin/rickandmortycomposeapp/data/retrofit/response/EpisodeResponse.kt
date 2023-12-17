package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.google.gson.annotations.SerializedName
import com.selin.rickandmortycomposeapp.data.retrofit.model.Info

data class EpisodeResponse(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val results: List<EpisodeResponseList>
)