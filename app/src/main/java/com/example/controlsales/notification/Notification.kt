package com.example.controlsales.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.example.controlsales.R


class Notification {


    companion object {

        val CHANNEL_ID = "chanel_id_example_01"
        val notificationId = 101;

         fun initNotification(context: Context, message: String) {
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                 val name = "Control Sales"
                 val importance = NotificationManager.IMPORTANCE_DEFAULT
                 val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                     message
                 }
                 val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                 notificationManager.createNotificationChannel(channel)
             }
        }

        fun sendNotification(context: Context) {
            val build = NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.google).setContentTitle("Novo cliente adicionado").setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(context)) {
                notify(notificationId, build.build())
            }
        }
    }

}