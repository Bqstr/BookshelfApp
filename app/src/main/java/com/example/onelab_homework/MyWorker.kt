package com.example.onelab_homework

import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Отправляем широковещательное сообщение
        val intent = Intent("com.example.MY_NOTIFICATION")
        context.sendBroadcast(intent)
        return Result.success()
    }
}