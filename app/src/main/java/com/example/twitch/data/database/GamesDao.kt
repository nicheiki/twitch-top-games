package com.example.twitch.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(games: List<Game>)

    @Query("SELECT * FROM games ORDER BY viewers DESC LIMIT :limit OFFSET :offset")
    suspend fun getList(offset: Int, limit: Int): List<Game>

    @Query("DELETE FROM games")
    suspend fun deleteAll()

}