package com.example.alarmclockapp

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Boolean.TRUE

class AudioPlayer {
    private var mp: MediaPlayer? = null

    // method for playing and stopping song
    @RequiresApi(Build.VERSION_CODES.O)
    fun playSong(context: Context?, stop: Boolean) {
        mp = MediaPlayer.create(context, R.raw.abcd)
        mp?.start()

        if (stop == TRUE)
        {
            mp?.stop()
        }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun stopSong(context: SetAlarm) {
//        mp?.stop()
//    }

    // method for playing a ringtone, if nog\ music is chosen
    fun ringTonePlayer(context: Context)
    {
        mp = MediaPlayer.create(context, R.raw.alarm)
        mp?.start()
    }
}