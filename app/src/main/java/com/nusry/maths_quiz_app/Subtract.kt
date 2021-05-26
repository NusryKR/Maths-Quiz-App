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
import kotlinx.android.synthetic.main.activity_subtract.*
import kotlinx.android.synthetic.main.activity_subtract.MenuBtn
import kotlin.random.Random

class Subtract : AppCompatActivity() {
    //Initialize Variable
    private var scaleUp: Animation? = null
    private var scaleDown: Animation? = null
    private var answersSub = ArrayList<Int>()
    private var randomSub = Random
    private var scoreSub = 0
    private var totalAttemptSub = 0
    private var percentageSub = 0f
    private var locationOfCorrectAnswerSub = 0
    private lateinit var countDownTimerSub: CountDownTimer

    //Function-Subtraction
    private fun newQuestionSub() {
        locationOfCorrectAnswerSub = randomSub.nextInt(4)
        val firstNumberSub = randomSub.nextInt(51)
        val secondNumberSub = randomSub.nextInt(51)
        val correctAnswerSub = firstNumberSub - secondNumberSub
        questionTextViewSub.text =
            "$firstNumberSub - $secondNumberSub"
        answersSub.clear()
        for (i in 0..3) {
            if (i == locationOfCorrectAnswerSub) {
                answersSub.add(correctAnswerSub)
            } else {
                var wrongAnswerSub = randomSub.nextInt(101)
                while (wrongAnswerSub == correctAnswerSub) {
                    wrongAnswerSub = randomSub.nextInt(101)
                }
                answersSub.add(wrongAnswerSub)
            }
        }
        buttonSub.text = answersSub[0].toString()
        button1Sub.text = answersSub[1].toString()
        button2Sub.text = answersSub[2].toString()
        button3Sub.text = answersSub[3].toString()
    }

    //Choose Answer Subtraction
    fun chooseAnswerSub(view: View) {
        if (locationOfCorrectAnswerSub.toString() == view.tag.toString()) {
            correctTextviewSub.text = "Correct Answer"
            scoreSub++
        } else {
            correctTextviewSub.text = "Wrong Answer"
        }
        totalAttemptSub++
        statusTextViewSub.text =
            "$scoreSub/$totalAttemptSub"
        percentageSub = (scoreSub * 100).toFloat() / totalAttemptSub
        newQuestionSub()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subtract)

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        //Animation
        buttonSub.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                buttonSub.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                buttonSub.startAnimation(scaleDown)
            }
            false
        }
        button1Sub.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button1Sub.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button1Sub.startAnimation(scaleDown)
            }
            false
        }
        button2Sub.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button2Sub.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button2Sub.startAnimation(scaleDown)
            }
            false
        }
        button3Sub.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button3Sub.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button3Sub.startAnimation(scaleDown)
            }
            false
        }
        startQuizButtonSub.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                startQuizButtonSub.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                startQuizButtonSub.startAnimation(scaleDown)
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

        //Activity-Subtraction
        startQuizButtonSub.setOnClickListener {
            constraintChildSub.visibility = View.VISIBLE
            startQuizButtonSub.visibility = View.GONE
            MenuBtn.visibility = View.GONE
            scoreSub = 0
            totalAttemptSub = 0
            statusTextViewSub.text = "0/0"
            newQuestionSub()
            countDownTimerSub = object : CountDownTimer(60200, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val one = millisUntilFinished.toInt()
                    val two = 1000
                    timerTextViewSub.text = ((one / two).toString() + "s")
                }


                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    constraintChildSub.visibility = View.GONE
                    startQuizButtonSub.visibility = View.VISIBLE
                    resultTextViewSub.visibility = View.VISIBLE
                    MenuBtn.visibility = View.VISIBLE
                    resultTextViewSub.text = "Time Up ! Your accuracy is $percentageSub"
                    startQuizButtonSub.text = "Play Again"
                }
            }.start()
        }//End Activity
    }
}