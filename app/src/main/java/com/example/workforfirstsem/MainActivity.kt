package com.example.workforfirstsem

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {

    private var service: NotificationService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = NotificationService()

        findViewById<Button>(R.id.btn_start).setOnClickListener{
            initAlarm()
        }
        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            cancelAlarm()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
    }

    private fun initAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationService::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            333,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val calendar = Calendar.getInstance()

        val hours = findViewById<EditText>(R.id.et_hours).text.toString().toInt()
        val minutes = findViewById<EditText>(R.id.et_minutes).text.toString().toInt()

        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)

        val interval = calendar.timeInMillis - System.currentTimeMillis()

        if (interval < 0) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
        }

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    private fun cancelAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationService::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            333,
            intent,
            0
        )
        alarmManager.cancel(pendingIntent)
    }
}
