package com.example.alarmclockapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class GameStart : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(GameView(this))
    }

    companion object {

        fun exitGame() {
            System.exit(0)
        }
    }

}