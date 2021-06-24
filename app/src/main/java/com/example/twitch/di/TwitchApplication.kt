package com.example.twitch.di

import android.app.Application
import com.example.twitch.data.database.GamesDatabase
import com.example.twitch.data.repository.Repository

class TwitchApplication: Application() {


    val repository by lazy {

        return@lazy Repository(
            GamesDatabase.getInstance(this).gamesDao
        )
    }


}