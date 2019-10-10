package com.example.alarmclockapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_password_option.*
import java.util.*

class PasswordOption : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_option)

        textView7.text = passwordGenerator()
    }

    // method checks if answer is correct
    fun solvedPassword(view: View)
    {
        val answer = passwordAnswer.text.toString()

        if (answer == passwordGenerator())
        {
            this.finish()
        } else {
            Toast.makeText(this, "Try harder!!" , Toast.LENGTH_SHORT).show()
        }
    }

    // generate random password
    private fun passwordGenerator(): String {
        return UUID.randomUUID().toString()
    }

    // blocks the return button, you have to solve the problem
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Back press disabled!", Toast.LENGTH_SHORT).show()
    }
}
