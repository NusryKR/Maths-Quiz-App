package com.nusry.maths_quiz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Intent to Addition
        frontAdditionButton.setOnClickListener {
            val intentAdd = Intent(this,Addition::class.java)
            startActivity(intentAdd)
        }
        //Intent to Subtraction
        frontSubscriptionButton.setOnClickListener {
            val intentSub = Intent(this,Subtract::class.java)
            startActivity(intentSub)
        }
        //Intent to Multiply
        frontMultiplicationButton.setOnClickListener {
            val intentMul = Intent(this,Multiply::class.java)
            startActivity(intentMul)
        }
        //Intent to Division
        frontDivisionButton.setOnClickListener {
            val intentDiv = Intent(this,Division::class.java)
            startActivity(intentDiv)
        }

    }
}