package com.example.dicerollapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */

class MainActivity : AppCompatActivity() {

    // find rollTextView by its indentify
    lateinit var diceImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage = findViewById(R.id.imageView)
        onListenRollButton()
        // roll when viewDidCreate
        configImageView(roll())
    }

    // set listener for rollButton
    fun onListenRollButton() {

        // find rollButton by its idtentitfy
        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener {
            configImageView(roll())
        }
    }

    /**
     * configImageView when roll finished
     */

    fun configImageView(imageId: Int) {
        // set ImageView with correctly resource
        diceImage.setImageResource(imageId)
        // Update the content description
        diceImage.contentDescription = imageId.toString()
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    fun roll(): Int {
        val dice = Dice(6)
        when (dice.rollDice()) {
            1 -> return R.drawable.dice_1
            2 -> return  R.drawable.dice_2
            3 -> return R.drawable.dice_3
            4 -> return R.drawable.dice_4
            5 -> return  R.drawable.dice_5
            else -> return R.drawable.dice_6
        }
    }
}

class Dice(private var numberSide: Int) {
    fun rollDice(): Int {
        return (1..numberSide).random()
    }
}