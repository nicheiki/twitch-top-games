package com.example.twitch.data.network

import com.example.twitch.data.models.Container
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TwitchService {

    @Headers("Accept: application/vnd.twitchtv.v5+json")
    @GET("games/top")
    suspend fun getApiModels(
        @Query("client_id") id: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Container

}