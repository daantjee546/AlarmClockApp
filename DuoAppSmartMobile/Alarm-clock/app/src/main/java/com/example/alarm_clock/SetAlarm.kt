package com.example.alarm_clock

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_set_alarm.*
import java.util.*




@Suppress("INTEGER_OVERFLOW")
class SetAlarm : AppCompatActivity() {

    var AlarmReceiverActivity = AlarmReceiver()

//    var lv: ListView? = null
    private var radioGroup: RadioGroup? = null

    var mp: MediaPlayer? = null

    var alarmTimePicker: TimePicker? = null
    var pendingIntent: PendingIntent? = null
    var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        alarmTimePicker = findViewById(R.id.timePicker)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager?
        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var radioGroupGames = findViewById(R.id.radioGroupGames) as RadioGroup
        var radioGroupAnnoying = findViewById(R.id.radioGroupAnnoying) as RadioGroup

        radioGroupGames!!.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // find which radio button is selected
                if (checkedId == R.id.MathProblem) {
                    Toast.makeText(
                        applicationContext, "choice: MathProblem    ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (checkedId == R.id.Puzzle) {
                    Toast.makeText(
                        applicationContext, "choice: Puzzle",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (checkedId == R.id.ReactionGame) {
                    Toast.makeText(
                        applicationContext, "choice: ReactionGame",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (checkedId == R.id.Password) {
                    Toast.makeText(
                        applicationContext, "choice: Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        radioGroupAnnoying!!.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {

            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // find which radio button is selected
                if (checkedId == R.id.Music) {
                    Toast.makeText(
                        applicationContext, "choice: Music",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (checkedId == R.id.vibration) {
                    Toast.makeText(
                        applicationContext, "choice: vibration",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (checkedId == R.id.Brightness){
                    Toast.makeText(
                        applicationContext, "choice: Brightness",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        //        lv = findViewById(R.id.lvOptions)
        addOptions()
    }

    private fun addOptions() {
//        val list = ArrayList<String>()
//        list.add("Math problem")
//        list.add("Puzzle")
//        list.add("Reaction game")
//        list.add("Password")
//        list.add("Music")
//        list.add("vibration")
//        list.add("Brightness")
//
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
//
//        lv?.setAdapter(adapter)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ShortAlarm")
    fun setAlarm(view: View) {

        if ((view as ToggleButton).isChecked) {
            Toast.makeText(this@SetAlarm, "ALARM ON", Toast.LENGTH_SHORT).show()

            val choice = null
            val intent = Intent(this, AlarmReceiver::class.java)

            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            val calendar = Calendar.getInstance()

            var hours = timePicker.hour
            var minutes = timePicker.minute

            calendar.timeInMillis = System.currentTimeMillis()
            calendar.set(Calendar.HOUR_OF_DAY, hours)
            calendar.set(Calendar.MINUTE, minutes)

//            val nextAlarm = "Next alarm: $hours:$minutes"
//            val mainActivity = MainActivity()
//            mainActivity.list(nextAlarm)

            alarmManager?.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent)
            
        }
        else {
            alarmManager?.cancel(pendingIntent)
            Toast.makeText(this@SetAlarm, "ALARM OFF", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dismissAlarm(view: View)
    {
        Toast.makeText(this@SetAlarm, "Dismiss alaram!!!", Toast.LENGTH_SHORT).show()

        //AudioPlayer().playSong(this)
        //this.AlarmReceiverActivity.stopSong()
        //AlarmReceiver().stopSong()
        //stopSong()
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun stopSong() {
//        mp?.stop()
//    }

}
