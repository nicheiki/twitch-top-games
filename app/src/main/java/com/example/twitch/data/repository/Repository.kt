package com.example.twitch.data.repository

import android.content.SharedPreferences
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.twitch.data.database.GamesDao
import com.example.twitch.data.database.Game
import com.example.twitch.data.database.GamesDatabase
import com.example.twitch.data.network.TwitchService
import kotlinx.coroutines.flow.Flow


class Repository(private val dao: GamesDao) {

    fun getGames(): Flow<PagingData<Game>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            GamesPagingSource(dao)
        }.flow
    }


}