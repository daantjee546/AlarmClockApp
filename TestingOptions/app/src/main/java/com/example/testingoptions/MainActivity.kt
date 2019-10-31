package com.example.testingoptions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.RadioGroup
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    private var radioGroup: RadioGroup? = null
    private val button: Button? = null
    private val textView: TextView? = null
    private val MathProblem = null
    private val Puzzle = null
    private val Music = null
    private val ReactionGame = null
    private val Password = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup) as RadioGroup

        radioGroup!!.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {

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
                } else if (checkedId == R.id.Music) {
                    Toast.makeText(
                        applicationContext, "choice: Music",
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
                } else if (checkedId == R.id.vibration) {
                    Toast.makeText(
                        applicationContext, "choice: vibration",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext, "choice: Brightness",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })


    }

    fun choice(view: View) {

        when (radioGroup?.checkedRadioButtonId) {
            R.id.MathProblem -> text.text = "You chose 'MathProblem' option"
            R.id.Puzzle -> text?.text = "You chose 'Puzzle' option"
            R.id.Music -> text?.text = "You chose 'Music' option"
            R.id.ReactionGame -> text?.text = "You chose 'ReactionGame' option"
            R.id.Password -> text?.text = "You chose 'Password' option"
            R.id.vibration -> text?.text = "You chose 'vibration' option"
            R.id.Brightness -> text?.text = "You chose 'Brightness' option"
        }
    }
}
