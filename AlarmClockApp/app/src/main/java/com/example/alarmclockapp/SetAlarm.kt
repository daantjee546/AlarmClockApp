package com.example.alarmclockapp

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlarmManager
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_set_alarm.*
import java.util.*
import android.widget.*
import java.lang.Boolean.TRUE

class SetAlarm : AppCompatActivity() {

    private var BA: BluetoothAdapter? = null
    private var alarmTimePicker: TimePicker? = null
    private var pendingIntent: PendingIntent? = null
    private var alarmManager: AlarmManager? = null
    private var lv: ListView? = null
    private var choiceRadioButtonGames: String = ""
    private var choiceRadioButtonAnnoying: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        alarmTimePicker = findViewById(R.id.timePicker)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager?
//        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val radioGroupGames = findViewById<RadioGroup>(R.id.radioGroupGames)
        val radioGroupAnnoying = findViewById<RadioGroup>(R.id.radioGroupAnnoying)

        // check's which radiobutton is checked in the current radioGroupBox (games)
        radioGroupGames!!.setOnCheckedChangeListener { group, checkedId ->
            // find which radio button is selected
            when (checkedId) {
                R.id.MathProblem -> Toast.makeText(
                    applicationContext, "choice: MathProblem", Toast.LENGTH_SHORT).show()
                R.id.Puzzle -> Toast.makeText(
                    applicationContext, "choice: Puzzle", Toast.LENGTH_SHORT).show()
                R.id.ReactionGame -> Toast.makeText(
                    applicationContext, "choice: ReactionGame", Toast.LENGTH_SHORT).show()
                R.id.Password -> Toast.makeText(
                    applicationContext, "choice: Password", Toast.LENGTH_SHORT).show()
            }
        }

        // check's which radiobutton is checked in the current radioGroupBox (annoying)
        radioGroupAnnoying!!.setOnCheckedChangeListener { group, checkedId ->
            // find which radio button is selected
            when (checkedId) {
                R.id.Music -> Toast.makeText(
                    applicationContext, "choice: Music", Toast.LENGTH_SHORT).show()
                R.id.vibration -> Toast.makeText(
                    applicationContext, "choice: vibration", Toast.LENGTH_SHORT).show()
                R.id.MusicBluetooth -> Toast.makeText(
                    applicationContext, "choice: MusicBluetooth", Toast.LENGTH_SHORT).show()
                R.id.Brightness -> Toast.makeText(
                    applicationContext, "choice: Brightness", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ShortAlarm")
    fun setAlarm(view: View) {

        choiceRadioButtonAnnoying = ""
        intent.removeExtra("keyAnnoying")

        if ((view as ToggleButton).isChecked) {
            Toast.makeText(this@SetAlarm, "ALARM ON", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, AlarmReceiver::class.java)
            val calendar = Calendar.getInstance()
            val hours = timePicker.hour // read hours from widget
            val minutes = timePicker.minute // read minutes from widget

            // set alarm on what hour and minute
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.set(Calendar.HOUR_OF_DAY, hours)
            calendar.set(Calendar.MINUTE, minutes)

            // check's for what option is checked and if needed opens an new activity
            when {
                radioGroupGames?.checkedRadioButtonId == R.id.Puzzle -> choiceRadioButtonGames = "Puzzle"
                radioGroupGames?.checkedRadioButtonId == R.id.MathProblem -> {
                    choiceRadioButtonGames = "MathProblem"
                    val intent1 = Intent(this, MathProblemA::class.java)
                    startActivity(intent1)
                }
                radioGroupGames?.checkedRadioButtonId == R.id.ReactionGame -> choiceRadioButtonGames = "ReactionGame"
                radioGroupGames?.checkedRadioButtonId == R.id.Password -> {
                    choiceRadioButtonGames = "Password"
                    val intent1 = Intent(this, PasswordOption::class.java)
                    startActivity(intent1)
                }
            }

            BA = BluetoothAdapter.getDefaultAdapter()

            // check's for what option is checked
            when {
                radioGroupAnnoying?.checkedRadioButtonId == R.id.Music -> choiceRadioButtonAnnoying = "Music"
                radioGroupAnnoying?.checkedRadioButtonId == R.id.MusicBluetooth -> {
                    if (!BA?.isEnabled!!) {
                        val turnOn = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                        startActivityForResult(turnOn, 0)
                        Toast.makeText(applicationContext, "Turned on", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext, "Already on", Toast.LENGTH_LONG).show()
                    }
                    choiceRadioButtonAnnoying = "MusicBluetooth"
                }
                radioGroupAnnoying?.checkedRadioButtonId == R.id.vibration -> choiceRadioButtonAnnoying = "vibration"
                radioGroupAnnoying?.checkedRadioButtonId == R.id.Brightness -> choiceRadioButtonAnnoying = "Brightness"
            }

            // send string to activity (AlarmReceiver), with key word: keyAnnoying
            intent.putExtra("keyAnnoying", choiceRadioButtonAnnoying)

            // creates a pendingIntent, for what activity the alarm have to trigger (intent = AlarmReceiver)
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
            // check's if alarm has to go off, if YES then AlarmReceiver gets enabled
            alarmManager?.setExact(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
            intent.removeExtra("keyAnnoying")

            val intent1 = Intent(this, MainActivity::class.java)
            intent1.putExtra("SetAlarms", "Hours: $hours Minutes: $minutes")


        }
        // if button clicked alarms get cancelled or dismissed when playing
        // doesn't know how yet????????
        else {
            alarmManager?.cancel(pendingIntent)
            Toast.makeText(this@SetAlarm, "ALARM OFF", Toast.LENGTH_SHORT).show()
            radioGroupAnnoying.clearCheck()
            radioGroupGames.clearCheck()
            //intent.removeExtra("keyGames")
            //intent.removeExtra("keyAnnoying")

            //AlarmReceiver().abortAlarm(TRUE)

            //AlarmReceiver().abortAlarm(TRUE)
            val intent = Intent(this, AlarmReceiver::class.java)
            intent.removeExtra("keyAnnoying")
            val intentRm = Intent(applicationContext, MainActivity::class.java)
            intentRm.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            AlarmReceiver().abortAlarm(TRUE)
            startActivity(intent)
            //AudioPlayer().playSong(this, TRUE)

//            val i = Intent(applicationContext, SetAlarm::class.java)        // Specify any activity here e.g. home or splash or login etc
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            i.putExtra("EXIT", true)
//            startActivity(i)
            finish()

        }

//        fun dismissAlarm(view: View) {
//            Toast.makeText(this@SetAlarm, "Dismiss alaram!!!", Toast.LENGTH_SHORT).show()
//
//            //AudioPlayer().playSong(this)
//            //this.AlarmReceiverActivity.stopSong()
//            //AlarmReceiver().stopSong()
//            //stopSong()
//
//            AlarmReceiver().abortBroadcast()
//        }
    }

    //single dismiss button
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun dismissAlarm(view: View) {
//        Toast.makeText(this@SetAlarm, "Dismiss alarm!!!", Toast.LENGTH_SHORT).show()
//        //AudioPlayer().stopSong(this)
//        //AlarmReceiver().abortBroadcast()
//
////        val intent = Intent(applicationContext, MainActivity::class.java)
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////        startActivity(intent)
//
//    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Back press disabled!", Toast.LENGTH_SHORT).show()
        //val intent1 = Intent(this, MainActivity::class.java)

        startActivity(Intent(this, MainActivity::class.java))
    }
}
