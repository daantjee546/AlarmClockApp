package com.example.wekkershit

import android.annotation.TargetApi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlarmManager
import android.app.PendingIntent
import android.widget.TimePicker
import android.widget.Toast
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.widget.ToggleButton

import android.view.View


class MainActivity : AppCompatActivity() {

    var alarmTimePicker: TimePicker? = null
    var pendingIntent: PendingIntent? = null
    var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun OnToggleClicked(view: View) {
        var time: Long
        if ((view as ToggleButton).isChecked) {
            Toast.makeText(this@MainActivity, "ALARM ON", Toast.LENGTH_SHORT).show()
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker!!.getCurrentHour())
            calendar.set(Calendar.MINUTE, alarmTimePicker!!.getCurrentMinute())
            val intent = Intent(this, AlarmReceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            time = calendar.getTimeInMillis() - calendar.getTimeInMillis() % 60000
            if (System.currentTimeMillis() > time) {
                if(calendar.get(Calendar.AM_PM) === 0)
                    time = time + 1000 * 60 * 60 * 12
                else
                    time = time + 1000 * 60 * 60 * 24
            }
            alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent)
        } else {
            alarmManager?.cancel(pendingIntent)
            Toast.makeText(this@MainActivity, "ALARM OFF", Toast.LENGTH_SHORT).show()
        }
    }
}
