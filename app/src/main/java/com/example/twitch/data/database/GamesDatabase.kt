package com.example.twitch.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.twitch.data.database.Game


@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {

    abstract val gamesDao: GamesDao

    companion object {

        @Volatile
        private var INSTANCE: GamesDatabase? = null

        fun getInstance(context: Context): GamesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GamesDatabase::class.java,
                    "games_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
