package com.example.alarmclockapp

import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

    var lv: ListView? = null
    private var nextAlarm = "No upcoming alarm"
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv = this.findViewById(R.id.lvUpcomingAlarms)

        // create audiomanager and out volume to max
        val audioManager =
            applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.mediaMaxVolume
        audioManager.setMediaVolume(maxVolume)

        if (count == 1)
        {
            val intent1 = Intent(this, SetAlarm::class.java)
            nextAlarm =  intent1.getStringExtra("SetAlarms")
            count--
        }
        else
        {
            nextAlarm = "No upcoming alarm"
            count++
        }

        list(nextAlarm)
    }

    fun setAlarmActivity(view: View)
    {
        startActivity(Intent(this, SetAlarm::class.java))
        finish()
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
