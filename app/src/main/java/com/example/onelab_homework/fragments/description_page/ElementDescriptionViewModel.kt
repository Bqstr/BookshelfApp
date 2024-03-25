package com.example.onelab_homework.fragments.description_page

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onelab_homework.MainActivity
import com.example.onelab_homework.R
import com.example.onelab_homework.database.BooksRepository
import kotlinx.coroutines.launch


class ElementDescriptionViewModel(val repository: BooksRepository , val context: Context): ViewModel() {

    var isFavorite = MutableLiveData<Boolean>()

    fun changeFavorite(name: String, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.changeFavorite(name, isFavorite)
        }
    }


}