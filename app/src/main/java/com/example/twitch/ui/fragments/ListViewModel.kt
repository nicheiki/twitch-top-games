package com.example.twitch.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.twitch.data.database.Game

import com.example.twitch.data.repository.Repository
import com.example.twitch.di.TwitchApplication
import kotlinx.coroutines.flow.Flow


class ListViewModel(twitchApplication: TwitchApplication) : ViewModel() {

    private val repository: Repository = twitchApplication.repository


    fun getGames(): Flow<PagingData<Game>> = repository.getGames().cachedIn(viewModelScope)
}

class ListViewModelFactory(private val app: TwitchApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return ListViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
