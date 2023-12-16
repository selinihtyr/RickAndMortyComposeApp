package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class EpisodeResponseList(
    @SerializedName("air_date") var airDate: String?,
    @SerializedName("characters") var characters: List<String>,
    @SerializedName("created") var created: String,
    @SerializedName("episode") var episode: String,
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
)