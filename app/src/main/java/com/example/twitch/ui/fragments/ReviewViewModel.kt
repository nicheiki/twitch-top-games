package com.example.twitch.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitch.ui.states.FeedbackSendingResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ReviewViewModel : ViewModel() {

    val sendingResult = MutableLiveData(FeedbackSendingResult.NOT_SEND)

    fun sendFeedback(rating: Float, review: String) {
        sendingResult.value = FeedbackSendingResult.IN_PROGRESS
        viewModelScope.launch {
            //Иммитация отправки на сервер
            delay(2000)
            sendingResult.postValue(FeedbackSendingResult.SUCCESS)
        }
    }
}