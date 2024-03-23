package com.example.onelab_homework

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onelab_homework.database.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ElementDescriptionViewModel(val repository: BooksRepository , val context: Context): ViewModel() {


    private val CHANNEL_ID = "channel_id"
    private val NOTIFICATION_ID = 123

    var isFavorite= MutableLiveData<Boolean>()

    fun changeFavorite(name:String ,isFavorite:Boolean){
        viewModelScope.launch {
        repository.changeFavorite(name ,isFavorite)
        }
    }

    fun sendMsg(bookName:String) {
        viewModelScope.launch(Dispatchers.IO) {

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Channel Name",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_timer_24)
                .setContentTitle("Time to read!")
                .setContentText("You need to read ${bookName}")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }

    }




}