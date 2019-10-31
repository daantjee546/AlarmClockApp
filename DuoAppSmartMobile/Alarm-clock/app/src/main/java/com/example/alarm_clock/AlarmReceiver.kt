package com.example.alarm_clock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

class AlarmReceiver : BroadcastReceiver() {

    //var mp: MediaPlayer? = null
    //private lateinit var audioManager: AudioManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show()

//        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show()
//        var alarmUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
//        if (alarmUri == null) {
//            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        }
//        val ringtone = RingtoneManager.getRingtone(context, alarmUri)
//        ringtone.play()

        //lateinit var audioManager: AudioManager
        //val maxVolume = audioManager.mediaMaxVolume
        //audioManager.setMediaVolume(100000)
        //mp = MediaPlayer.create(context, R.raw.abcd)

        AudioPlayer().playSong(context)
        //playSong()
    }
}
