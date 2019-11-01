package com.example.alarmclockapp

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlarmManager
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.RadioGroup
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_set_alarm.*
import java.lang.Boolean.TRUE
import java.util.*

var choiceRadioButtonAnnoying: String = "No choice for annoying"
var choiceRadioButtonGames: String = "No choice for game"

class SetAlarm : AppCompatActivity() {

    private var alarmTimePicker: TimePicker? = null
    private var pendingIntent: PendingIntent? = null
    private var alarmManager: AlarmManager? = null
    var BA: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        if (!canWrite) {
            allowWritePermission()
        }
        alarmTimePicker = findViewById(R.id.timePicker)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager?

        val radioGroupGames = findViewById<RadioGroup>(R.id.radioGroupGames)
        val radioGroupAnnoying = findViewById<RadioGroup>(R.id.radioGroupAnnoying)

        // check's which radiobutton is checked in the current radioGroupBox (games)
        radioGroupGames!!.setOnCheckedChangeListener { group, checkedId ->
            // find which radio button is selected
            when (checkedId) {
                R.id.MathProblem -> Toast.makeText(
                    applicationContext, "choice: MathProblem", Toast.LENGTH_SHORT
                ).show()
                R.id.Puzzle -> Toast.makeText(
                    applicationContext, "choice: Puzzle", Toast.LENGTH_SHORT
                ).show()
                R.id.ReactionGame -> Toast.makeText(
                    applicationContext, "choice: ReactionGame", Toast.LENGTH_SHORT
                ).show()
                R.id.Password -> Toast.makeText(
                    applicationContext, "choice: Password", Toast.LENGTH_SHORT
                ).show()
            }
        }

        // check's which radiobutton is checked in the current radioGroupBox (annoying)
        radioGroupAnnoying!!.setOnCheckedChangeListener { group, checkedId ->
            // find which radio button is selected
            when (checkedId) {
                R.id.Music -> Toast.makeText(
                    applicationContext, "choice: Music", Toast.LENGTH_SHORT
                ).show()
                R.id.vibration -> Toast.makeText(
                    applicationContext, "choice: vibration", Toast.LENGTH_SHORT
                ).show()
                R.id.MusicBluetooth -> Toast.makeText(
                    applicationContext, "choice: MusicBluetooth", Toast.LENGTH_SHORT
                ).show()
                R.id.Brightness -> Toast.makeText(
                    applicationContext, "choice: Brightness", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ShortAlarm")
    fun setAlarm(view: View) {

        choiceRadioButtonAnnoying = ""
        choiceRadioButtonGames = ""

        if ((view as ToggleButton).isChecked) {
            Toast.makeText(this, "ALARM ON", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, AlarmReceiver::class.java)
            val calendar = Calendar.getInstance()
            val hours = timePicker.hour // read hours from widget
            val minutes = timePicker.minute // read minutes from widget

            // set alarm on what hour and minute
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.set(Calendar.HOUR_OF_DAY, hours)
            calendar.set(Calendar.MINUTE, minutes)

            BA = BluetoothAdapter.getDefaultAdapter()

            // check's for what annonying option is checked
            when {
                radioGroupAnnoying?.checkedRadioButtonId == R.id.Music -> choiceRadioButtonAnnoying =
                    "Music"
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
                radioGroupAnnoying?.checkedRadioButtonId == R.id.vibration -> choiceRadioButtonAnnoying =
                    "vibration"
                radioGroupAnnoying?.checkedRadioButtonId == R.id.Brightness -> {
                    setBrightness(255)
                    choiceRadioButtonAnnoying = "Brightness"
                }
            }

            // check's for what game option is checked and if needed opens an new activity
            when {
                radioGroupGames.checkedRadioButtonId == R.id.Puzzle -> choiceRadioButtonGames =
                    "Puzzle"
                radioGroupGames.checkedRadioButtonId == R.id.MathProblem -> choiceRadioButtonGames =
                    "MathProblem"
                radioGroupGames.checkedRadioButtonId == R.id.ReactionGame -> choiceRadioButtonGames =
                    "ReactionGame"
                radioGroupGames.checkedRadioButtonId == R.id.Password -> choiceRadioButtonGames =
                    "Password"
            }

            // creates a pendingIntent, for what activity the alarm have to trigger (intent = AlarmReceiver)
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            // check's if alarm has to go off, if YES then AlarmReceiver gets enabled
            alarmManager?.setExact(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)

            // update alarms on start screen write it to FireBase
            //val intent1 = Intent(this, MainActivity::class.java)
            //intent1.putExtra("SetAlarms", "Hours: $hours Minutes: $minutes")
        } else {
            Toast.makeText(this@SetAlarm, "ALARM OFF", Toast.LENGTH_SHORT).show()

            radioGroupAnnoying.clearCheck()
            radioGroupGames.clearCheck()

            AudioPlayer().playSong(this, TRUE)

            val i = Intent(
                applicationContext,
                SetAlarm::class.java
            )        // Specify any activity here e.g. home or splash or login etc
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            AudioPlayer().playSong(this, TRUE)
            startActivity(i)
            AudioPlayer().playSong(this, TRUE)
            finish()
            AudioPlayer().playSong(this, TRUE)
        }
    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Back press disabled!", Toast.LENGTH_SHORT).show()
        //val intent1 = Intent(this, MainActivity::class.java)

        startActivity(Intent(this, MainActivity::class.java))
    }
}

fun Context.allowWritePermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val intent = Intent(
            Settings.ACTION_MANAGE_WRITE_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivity(intent)
    }
}

val Context.canWrite: Boolean
    get() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.System.canWrite(this)
        } else {
            return true
        }
    }

fun Context.setBrightness(value: Int): Unit {
    Settings.System.putInt(
        this.contentResolver,
        Settings.System.SCREEN_BRIGHTNESS,
        value
    )
}