package com.example.alarmclockapp

import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var lv: ListView? = null
    private val nextAlarm = "No upcoming alarm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv = this.findViewById(R.id.lvUpcomingAlarms)

        list(nextAlarm)

        // create audiomanager and out volume to max
        val audioManager =
            applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.mediaMaxVolume
        audioManager.setMediaVolume(maxVolume)
    }

    fun setAlarmActivity(view: View)
    {
        startActivity(Intent(this, SetAlarm::class.java))
    }

    private fun list(alarm: String)
    {
        // create a list with upcoming alarms ans shows them in listview
        val list = ArrayList<String>()
        list.add(alarm)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        lv?.adapter = adapter
    }

    private fun AudioManager.setMediaVolume(volumeIndex:Int) {
        // Set media volume level
        this.setStreamVolume(
            AudioManager.STREAM_MUSIC,
            volumeIndex,
            AudioManager.FLAG_SHOW_UI
        )
    }

    private val AudioManager.mediaMaxVolume:Int
        get() = this.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
}
