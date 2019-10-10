package com.example.alarmclockapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_math_problem.*


class MathProblemA : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_problem)

        textView4.text = "MathProblem"
        textView5.text = generateMathSum()
    }

    private var number1: Int = 0
    private var number2: Int = 0
    private var operator = ""

    // check if the answer is good, otherwise try again
    @RequiresApi(Build.VERSION_CODES.O)
    fun solved(view: View)
    {
        val answer = answerBox.text.toString()

        if (answer == (number1 + number2).toString())
        {
            this.finish()
        } else {
            Toast.makeText(this, "Try harder!!" , Toast.LENGTH_SHORT).show()
        }
    }

    // create a random math sum, maybe extend with * / -
    private fun generateMathSum(): String {

        number1 = (Math.random() * 20).toInt() + 1
        number2 = (Math.random() * 20).toInt() + 1
        operator = "+"

        return number1.toString() + operator + number2.toString()
    }

    // blocks the return button, you have to solve the problem
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Back press disabled!", Toast.LENGTH_SHORT).show()
    }
}
