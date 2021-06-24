package com.example.twitch.data.models

import com.google.gson.annotations.SerializedName

data class ApiModel(
    @field:SerializedName("channels") val channels: Int,
    @field:SerializedName("game") val game: Info,
    @field:SerializedName("viewers") val viewers: Int
) {

    data class Info(
        val _id: Int,
        val box: Box,
        val giantbomb_id: Int,
        val logo: Logo,
        val name: String
    ) {
        data class Box(
            val large: String,
            val medium: String,
            val small: String,
            val template: String
        )

        data class Logo(
            val large: String,
            val medium: String,
            val small: String,
            val template: String
        )
    }
}

data class Container(val _total: Int, @SerializedName("top") val games: List<ApiModel>)
