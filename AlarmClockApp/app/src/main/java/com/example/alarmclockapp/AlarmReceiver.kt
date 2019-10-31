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
import android.content.Intent.getIntent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.core.content.ContextCompat.startActivity
import com.example.alarmclockapp.R.id.Puzzle
import kotlinx.android.synthetic.main.activity_set_alarm.*

@Suppress("DEPRECATION")
class AlarmReceiver : BroadcastReceiver() {

    private var choiceAnnoying: String? = "No choice for annoying"
    private var choiceGame: String? = "No choice for game"

    // calls when alarm has to go off
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        choiceAnnoying = choiceRadioButtonAnnoying
        choiceGame = choiceRadioButtonGames
        //choiceAnnoying = intent.getStringExtra("keyAnnoying")
        //choiceGame = intent.getStringExtra("keyGame")

        // toast message for printing which choice is maked
        Toast.makeText(context, "ChoiceAnnoying: $choiceAnnoying" , Toast.LENGTH_SHORT).show()

//        if (choiceGame == "Puzzle")
//        {
//            // niet uitgewerkt
//        }
//        else if (choiceGame == "MathProblem")
//        {
//            val intent1 = Intent(context, MathProblemA::class.java)
//            context.startActivity(intent1)
//        }
//        else if (choiceGame == "ReactionGame")
//        {
//            val intent1 = Intent(context, GameStart::class.java)
//            context.startActivity(intent1)
//        }
//        else if (choiceGame == "Password")
//        {
//            val intent1 = Intent(context, PasswordOption::class.java)
//            context.startActivity(intent1)
//        }

        val set = SetAlarm()
//        set.radioGroupGameChecker(choiceGame!!)

        if (choiceGame == "Puzzle")
        {
            // not implemented
        }
        else if (choiceGame == "MathProblem") {
            val intent1 = Intent(context, MathProblemA::class.java)
//            startActivity(intent1)
        }
        else if (choiceGame == "ReactionGame")
        {
            choiceRadioButtonGames = "ReactionGame"
            val intent1 = Intent(context, GameStart::class.java)
            //startActivity(intent1)
        }
        else if (choiceGame == "Password") {
            choiceRadioButtonGames = "Password"
            val intent1 = Intent(context, PasswordOption::class.java)
            //startActivity(intent1)
        }

//        SetAlarm().radioGroupGameChecker()

        if (choiceAnnoying == "Music" // play music on current device
        ) AudioPlayer().playSong(context, FALSE)
        else if (choiceAnnoying == "vibration") {
            // current device starting vibrating
            AudioPlayer().ringTonePlayer(context)
            vibrator(context)
        }
        else if (choiceAnnoying == "Brightness" // screen going to full brightness
        ) AudioPlayer().ringTonePlayer(context)
        else if (choiceAnnoying == "MusicBluetooth" // play music on bluetooth device
        ) AudioPlayer().playSong(context, FALSE)
        else AudioPlayer().ringTonePlayer(context)
        intent.removeExtra("keyAnnoying")
        getIntent("").removeExtra("keyAnnoying")
    }

    // if dismiss button is clicked, alarm have to stop
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