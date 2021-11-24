package com.example.workforfirstsem

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RawRes
import androidx.core.app.NotificationCompat

private const val CHANNEL_ID = "channel_id_1"

class NotificationService: BroadcastReceiver() {

    private val audio by lazy {
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
    }

    private fun Context.getSoundUri(
        @RawRes id: Int
    ) = Uri.parse("android.resource://${packageName}/$id")

    override fun onReceive(context: Context, intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.notification_channel_title),
                IMPORTANCE_HIGH
            ).apply {
                val sound: Uri = context.getSoundUri(R.raw.sound)
                setSound(sound, audio)
            }.also {
                notificationManager.createNotificationChannel(it)
            }
        }

        val intent = Intent(context, MainActivity2::class.java).let {
            PendingIntent.getActivities(
                context,
                123,
                arrayOf(it),
                PendingIntent.FLAG_ONE_SHOT
            )
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_alarm_24)
            .setContentTitle("\"Будильник\"")
            .setAutoCancel(true)
            .setContentIntent(intent)
            .setContentText("Пора просыпаться!!!")
            .setPriority(NotificationCompat.PRIORITY_MAX)
        val am = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = System.currentTimeMillis() / 1000
        am.notify(id.toInt(), builder.build())
    }
}
