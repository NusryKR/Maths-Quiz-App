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
import kotlinx.android.synthetic.main.activity_addition.MenuBtn
import kotlinx.android.synthetic.main.activity_division.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_subtract.*
import kotlin.random.Random

class Addition : AppCompatActivity() {
    //Initialize Variable
    private var scaleUp: Animation? = null
    private var scaleDown: Animation? = null
    private var answersAdd = ArrayList<Int>()
    private var randomAdd = Random
    private var scoreAdd = 0
    private var totalAttemptAdd = 0
    private var percentageAdd = 0f
    private var locationOfCorrectAnswerAdd = 0
    private lateinit var countDownTimerAdd: CountDownTimer

    //Function-Addition
    private fun newQuestionAdd() {
        locationOfCorrectAnswerAdd = randomAdd.nextInt(4)
        val firstNumberAdd = randomAdd.nextInt(51)
        val secondNumberAdd = randomAdd.nextInt(51)
        val correctAnswerAdd = firstNumberAdd + secondNumberAdd
        questionTextViewAdd.text =
            "$firstNumberAdd + $secondNumberAdd"
        answersAdd.clear()
        for (i in 0..3) {
            if (i == locationOfCorrectAnswerAdd) {
                answersAdd.add(correctAnswerAdd)
            } else {
                var wrongAnswerAdd = randomAdd.nextInt(101)
                while (wrongAnswerAdd == correctAnswerAdd) {
                    wrongAnswerAdd = randomAdd.nextInt(101)
                }
                answersAdd.add(wrongAnswerAdd)
            }
        }
        buttonAdd.text = answersAdd[0].toString()
        button1Add.text = answersAdd[1].toString()
        button2Add.text = answersAdd[2].toString()
        button3Add.text = answersAdd[3].toString()
    }

    //Choose Answer Addition
    fun chooseAnswerAdd(view: View) {
        if (locationOfCorrectAnswerAdd.toString() == view.tag.toString()) {
            correctTextviewAdd.text = "Correct Answer"
            scoreAdd++
        } else {
            correctTextviewAdd.text = "Wrong Answer"
        }
        totalAttemptAdd++
        statusTextViewAdd.text =
            "$scoreAdd/$totalAttemptAdd"
        percentageAdd = (scoreAdd * 100).toFloat() / totalAttemptAdd
        newQuestionAdd()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        //Animation
        buttonAdd.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                buttonAdd.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                buttonAdd.startAnimation(scaleDown)
            }
            false
        }
        button1Add.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button1Add.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button1Add.startAnimation(scaleDown)
            }
            false
        }
        button2Add.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button2Add.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button2Add.startAnimation(scaleDown)
            }
            false
        }
        button3Add.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button3Add.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button3Add.startAnimation(scaleDown)
            }
            false
        }
        startQuizButtonAdd.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                startQuizButtonAdd.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                startQuizButtonAdd.startAnimation(scaleDown)
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

        //Activity-Addition
        startQuizButtonAdd.setOnClickListener {
            constraintChildAdd.visibility = View.VISIBLE
            startQuizButtonAdd.visibility = View.GONE
            MenuBtn.visibility = View.GONE
            scoreAdd = 0
            totalAttemptAdd = 0
            statusTextViewAdd.text = "0/0"
            newQuestionAdd()
            countDownTimerAdd = object : CountDownTimer(60200, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val one = millisUntilFinished.toInt()
                    val two = 1000
                    timerTextViewAdd.text = ((one / two).toString() + "s")
                }


                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    constraintChildAdd.visibility = View.GONE
                    startQuizButtonAdd.visibility = View.VISIBLE
                    resultTextViewAdd.visibility = View.VISIBLE
                    MenuBtn.visibility = View.VISIBLE
                    resultTextViewAdd.text = "Time Up ! Your accuracy is $percentageAdd"
                    startQuizButtonAdd.text = "Play Again"
                }
            }.start()
        }//End Activity
    }
}