package com.example.onelab_homework.reciver

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.example.onelab_homework.MainActivity
import com.example.onelab_homework.R
//import com.example.onelab_homework.App.applicationContext


class MyReceiver : BroadcastReceiver() {

    override fun onReceive( context: Context, intent: Intent) {
        Log.d("dkndksnsfdndskf","aaaaaaaaaaaaaaaaaaaaaa")
        // Get the message from the intent
        // Get the message from the intent
        val message = intent.getStringExtra("message")
        // Show a toast with the received message
        // Show a toast with the received message
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()



        if (message != null) {
            showNotification(context,message)
        }













    }
    private fun showNotification(applicationContext:Context ,bookName:String){
        Log.d("messsage" ,"sended")
        val intent = Intent(applicationContext , MainActivity::class.java).apply {

            flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent  = PendingIntent.getActivity(applicationContext ,0, intent ,0)


        val notification = Notification.Builder(applicationContext , CHANEL_ID)
            .setSmallIcon(R.drawable.baseline_timer_24)
            .setContentTitle("Time to read new book!")
            .setContentText("You need to read $bookName")
            .setPriority(Notification.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val chanel_name ="chanel name"
            val chanelDescription ="chanel des"
            val chanel_importance = NotificationManager.IMPORTANCE_HIGH



            val chanel = NotificationChannel(CHANEL_ID,chanel_name,chanel_importance).apply {
                description =chanelDescription
            }
            val notificationManager =applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(chanel)

            with(NotificationManagerCompat.from(applicationContext)){
                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                }
                notify(NOTIFICATION ,notification.build())
            }
        }


    }

    companion object{
        const val CHANEL_ID ="chanel_id"
        const val NOTIFICATION =1
    }


    }



