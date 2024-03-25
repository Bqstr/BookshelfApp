package com.example.onelab_homework

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context : Context, workerParameters: WorkerParameters ) : Worker(context ,workerParameters){
    override fun doWork(): Result {

        val bookName =inputData.getString("HEREKEY")
        val intent = Intent("my_custom_action")
        Log.d("succces" ,bookName ?: "nothing")

        intent.putExtra("message", bookName)

        applicationContext.sendBroadcast(intent)
        return Result.success()


    }




}