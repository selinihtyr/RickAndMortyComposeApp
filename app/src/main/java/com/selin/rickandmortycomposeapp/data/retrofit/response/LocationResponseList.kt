package com.selin.rickandmortycomposeapp.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class LocationResponseList(
    @SerializedName("created") var created: String,
    @SerializedName("dimension") var dimension: String,
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("residents") var residents: List<String>,
    @SerializedName("type") var type: String,
    @SerializedName("url") var url: String
)