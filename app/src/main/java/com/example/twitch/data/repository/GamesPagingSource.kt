package com.example.twitch.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.twitch.data.database.Game
import com.example.twitch.data.database.GamesDao
import com.example.twitch.data.network.Client
import retrofit2.HttpException
import java.io.IOException

//Вписать ключ
const val CLIENT_ID = ""
private const val STARTING_PAGE_INDEX = 0
private const val LIMIT = 20

class GamesPagingSource(private val dao: GamesDao) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {

        val position = params.key ?: STARTING_PAGE_INDEX

        return try {

            var games = dao.getList(position, LIMIT)
            if (games.size < LIMIT) {
                games = Client.apiClient.getApiModels(CLIENT_ID, position, LIMIT).games.map {
                    Game(it.game.name, it.channels, it.viewers, it.game.box.large)
                }
                dao.insertList(games)
            }
            LoadResult.Page(
                data = games,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - LIMIT,
                nextKey = if (games.isEmpty()) null else position + LIMIT
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}