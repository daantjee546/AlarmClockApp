package com.example.ringtoneplayer

import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val defaultRingtone = RingtoneManager.getRingtone(this,
            Settings.System.DEFAULT_RINGTONE_URI
        )
        //fetch current Ringtone
        val currentRintoneUri = RingtoneManager.getActualDefaultRingtoneUri(this
            .applicationContext, RingtoneManager.TYPE_RINGTONE)
        val currentRingtone = RingtoneManager.getRingtone(this, currentRintoneUri)
        //play current Ringtone
        currentRingtone.play()
    }
}
