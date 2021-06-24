package com.example.twitch.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "channels") val channels: Int,
    @ColumnInfo(name = "viewers") val viewers: Int,
    @ColumnInfo(name = "imageUrl") val image: String
)
