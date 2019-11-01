package com.example.alarmclockapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Boolean.FALSE
import android.content.Intent.getIntent
@Suppress("DEPRECATION")
class AlarmReceiver : BroadcastReceiver() {

    private var choiceAnnoying: String? = "No choice for annoying"
    private var choiceGame: String? = "No choice for game"

    // calls when alarm has to go off
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        choiceAnnoying = choiceRadioButtonAnnoying
        choiceGame = choiceRadioButtonGames

        // toast message for printing which choice is maked
        Toast.makeText(context, "ChoiceAnnoying: $choiceAnnoying" , Toast.LENGTH_SHORT).show()// play music on bluetooth device

        // play music on current device
        when (choiceGame) {
            "Puzzle" -> {
                // not implemented
            }
            "MathProblem" -> {
                val intent1 = Intent(context, MathProblemA::class.java)
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent1)
            }
            "ReactionGame" -> {
                choiceRadioButtonGames = "ReactionGame"
                val intent1 = Intent(context, GameStart::class.java)
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent1)
            }
            "Password" -> {
                choiceRadioButtonGames = "Password"
                val intent1 = Intent(context, PasswordOption::class.java)
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent1)
            }
        }

        when (choiceAnnoying) {
            "Music" // play music on current device
            -> AudioPlayer().playSong(context, FALSE)
            "vibration" -> {
                // current device starting vibrating
                AudioPlayer().ringTonePlayer(context)
                vibrator(context)
            }
            "Brightness" // screen going to full brightness
            -> AudioPlayer().ringTonePlayer(context)
            "MusicBluetooth" // play music on bluetooth device
            -> AudioPlayer().playSong(context, FALSE)
            else -> AudioPlayer().ringTonePlayer(context)
        }
        intent.removeExtra("keyAnnoying")
        getIntent("").removeExtra("keyAnnoying")
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