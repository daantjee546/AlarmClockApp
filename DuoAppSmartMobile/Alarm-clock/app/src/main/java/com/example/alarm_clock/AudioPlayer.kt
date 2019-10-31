package com.example.alarm_clock

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.annotation.RequiresApi

class AudioPlayer {
    private var mp: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun playSong(context: Context) {
        mp = MediaPlayer.create(context, R.raw.abcd)
        mp?.start()
        //Thread.sleep(5000)
        //stopSong()
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun stopSong() {
//        mp?.stop()
//    }
    
    private fun AudioManager.setMediaVolume(volumeIndex:Int) {
        // Set media volume level
        this.setStreamVolume(
            AudioManager.STREAM_MUSIC, // Stream type
            volumeIndex, // Volume index
            AudioManager.FLAG_SHOW_UI// Flags
        )
    }

    val AudioManager.mediaMaxVolume:Int
        get() = this.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
}