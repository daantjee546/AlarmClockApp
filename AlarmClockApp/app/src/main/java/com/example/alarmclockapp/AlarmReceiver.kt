package com.example.alarmclockapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE


@Suppress("DEPRECATION")
class AlarmReceiver : BroadcastReceiver() {

    private var choiceAnnoying: String? = "No choice for annoying"

    // calls when alarm has to go off
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        choiceAnnoying = intent.getStringExtra("keyAnnoying")

        // toast message for printing which choice is maked
        Toast.makeText(context, "Choice: $choiceAnnoying" , Toast.LENGTH_SHORT).show()

        when (choiceAnnoying) {
            "Music" -> // play music on current device
                AudioPlayer().playSong(context, FALSE)
            "vibration" -> {
                // current device starting vibrating
                AudioPlayer().ringTonePlayer(context)
                vibrator(context)
            }
            "Brightness" -> // screen going to full brightness
                AudioPlayer().ringTonePlayer(context)
            "MusicBluetooth" -> // play music on bluetooth device
                AudioPlayer().playSong(context, FALSE)
            else -> AudioPlayer().ringTonePlayer(context)
        }
        intent.removeExtra("keyAnnoying")
    }

    // if dismiss button is clicked, alarm have to stopped
    fun abortAlarm (bool: Boolean)
    {
        if (bool == TRUE)
        {
            abortBroadcast()
        }
    }

    // method fot vibrating phone
    private fun vibrator(context: Context)
    {
        val vibe = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        AudioPlayer().ringTonePlayer(context)
        for (i in 40 downTo 0 step 1) {
            if (vibe.hasVibrator()) {
                vibe.vibrate(250)
            }
            Thread.sleep(250)
        }
    }
}