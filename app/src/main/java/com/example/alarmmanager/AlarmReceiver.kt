package com.example.alarmmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver:BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("enter","enter")
        var intent=Intent(context,MainActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        var pendingIntent=PendingIntent.getActivity(context,0,intent,0)

        var builder=NotificationCompat.Builder(context!!,"notify")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("alarmManager")
            .setSubText("welcome")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        var notificationChannel=NotificationChannel("notify","notification",NotificationManager.IMPORTANCE_DEFAULT)
        var notifcationmanager=NotificationManagerCompat.from(context)
        notifcationmanager.createNotificationChannel(notificationChannel)
        notifcationmanager.notify(1,builder.build())
    }

}