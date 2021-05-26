package com.nusry.maths_quiz_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_addition.*
import kotlinx.android.synthetic.main.activity_division.*
import kotlinx.android.synthetic.main.activity_division.MenuBtn
import kotlinx.android.synthetic.main.activity_subtract.*

import kotlin.random.Random

class Division : AppCompatActivity() {
    //Initialize Variable
    private var scaleUp: Animation? = null
    private var scaleDown: Animation? = null
    private var answersDiv = ArrayList<Int>()
    private var randomDiv = Random
    private var scoreDiv = 0
    private var totalAttemptDiv = 0
    private var percentageDiv = 0f
    private var locationOfCorrectAnswerDiv = 0
    private lateinit var countDownTimerDiv: CountDownTimer

    //Function-Division
    private fun newQuestionDiv() {
        locationOfCorrectAnswerDiv = randomDiv.nextInt(4)
        val firstNumberDiv = randomDiv.nextInt(51)
        val secondNumberDiv = randomDiv.nextInt(51)
        val correctAnswerDiv = firstNumberDiv / secondNumberDiv
        questionTextViewDiv.text =
            "$firstNumberDiv / $secondNumberDiv"
        answersDiv.clear()
        for (i in 0..3) {
            if (i == locationOfCorrectAnswerDiv) {
                answersDiv.add(correctAnswerDiv)
            } else {
                var wrongAnswerDiv = randomDiv.nextInt(101)
                while (wrongAnswerDiv == correctAnswerDiv) {
                    wrongAnswerDiv = randomDiv.nextInt(101)
                }
                answersDiv.add(wrongAnswerDiv)
            }
        }
        buttonDiv.text = answersDiv[0].toString()
        button1Div.text = answersDiv[1].toString()
        button2Div.text = answersDiv[2].toString()
        button3Div.text = answersDiv[3].toString()
    }

    //Choose Answer Division
    fun chooseAnswerDiv(view: View) {
        if (locationOfCorrectAnswerDiv.toString() == view.tag.toString()) {
            correctTextviewDiv.text = "Correct Answer"
            scoreDiv++
        } else {
            correctTextviewDiv.text = "Wrong Answer"
        }
        totalAttemptDiv++
        statusTextViewDiv.text =
            "$scoreDiv/$totalAttemptDiv"
        percentageDiv = (scoreDiv * 100).toFloat() / totalAttemptDiv
        newQuestionDiv()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_division)

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        //Animation
        buttonDiv.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                buttonDiv.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                buttonDiv.startAnimation(scaleDown)
            }
            false
        }
        button1Div.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button1Div.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button1Div.startAnimation(scaleDown)
            }
            false
        }
        button2Div.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button2Div.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button2Div.startAnimation(scaleDown)
            }
            false
        }
        button3Div.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button3Div.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button3Div.startAnimation(scaleDown)
            }
            false
        }
        startQuizButtonDiv.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                startQuizButtonDiv.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                startQuizButtonDiv.startAnimation(scaleDown)
            }
            false
        }
        MenuBtn.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                MenuBtn.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                MenuBtn.startAnimation(scaleDown)
            }
            false
        }//End Animation

        //Back To Main Activity
        MenuBtn.setOnClickListener {
            val intentMain = Intent(this,MainActivity::class.java)
            startActivity(intentMain)
        }

        //Activity-Division
        startQuizButtonDiv.setOnClickListener {
            constraintChildDiv.visibility = View.VISIBLE
            startQuizButtonDiv.visibility = View.GONE
            MenuBtn.visibility = View.GONE
            scoreDiv = 0
            totalAttemptDiv = 0
            statusTextViewDiv.text = "0/0"
            newQuestionDiv()
            countDownTimerDiv = object : CountDownTimer(60200, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val one = millisUntilFinished.toInt()
                    val two = 1000
                    timerTextViewDiv.text = ((one / two).toString() + "s")
                }


                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    constraintChildDiv.visibility = View.GONE
                    startQuizButtonDiv.visibility = View.VISIBLE
                    resultTextViewDiv.visibility = View.VISIBLE
                    MenuBtn.visibility = View.VISIBLE
                    resultTextViewDiv.text = "Time Up ! Your accuracy is $percentageDiv"
                    startQuizButtonDiv.text = "Play Again"
                }
            }.start()
        }//End Activity

    }
}