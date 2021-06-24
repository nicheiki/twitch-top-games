package com.example.twitch.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.twitch.tv/kraken/"

object Client {
    val apiClient: TwitchService by lazy {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return@lazy retrofit.create(TwitchService::class.java)
    }
}
