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
import kotlinx.android.synthetic.main.activity_multiply.*
import kotlinx.android.synthetic.main.activity_multiply.MenuBtn
import kotlinx.android.synthetic.main.activity_subtract.*
import kotlin.random.Random

class Multiply : AppCompatActivity() {
    //Initialize Variable
    private var scaleUp: Animation? = null
    private var scaleDown: Animation? = null
    private var answersMul = ArrayList<Int>()
    private var randomMul = Random
    private var scoreMul = 0
    private var totalAttemptMul = 0
    private var percentageMul = 0f
    private var locationOfCorrectAnswerMul = 0
    private lateinit var countDownTimerMul: CountDownTimer

    //Function-Multiplication
    private fun newQuestionMul() {
        locationOfCorrectAnswerMul = randomMul.nextInt(4)
        val firstNumberMul = randomMul.nextInt(51)
        val secondNumberMul = randomMul.nextInt(51)
        val correctAnswerMul = firstNumberMul * secondNumberMul
        questionTextViewMul.text =
            "$firstNumberMul X $secondNumberMul"
        answersMul.clear()
        for (i in 0..3) {
            if (i == locationOfCorrectAnswerMul) {
                answersMul.add(correctAnswerMul)
            } else {
                var wrongAnswerMul = randomMul.nextInt(101)
                while (wrongAnswerMul == correctAnswerMul) {
                    wrongAnswerMul = randomMul.nextInt(101)
                }
                answersMul.add(wrongAnswerMul)
            }
        }
        buttonMul.text = answersMul[0].toString()
        button1Mul.text = answersMul[1].toString()
        button2Mul.text = answersMul[2].toString()
        button3Mul.text = answersMul[3].toString()
    }

    //Choose Answer Multiplication
    fun chooseAnswerMul(view: View) {
        if (locationOfCorrectAnswerMul.toString() == view.tag.toString()) {
            correctTextviewMul.text = "Correct Answer"
            scoreMul++
        } else {
            correctTextviewMul.text = "Wrong Answer"
        }
        totalAttemptMul++
        statusTextViewMul.text =
            "$scoreMul/$totalAttemptMul"
        percentageMul = (scoreMul * 100).toFloat() / totalAttemptMul
        newQuestionMul()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiply)

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        //Animation
        buttonMul.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                buttonMul.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                buttonMul.startAnimation(scaleDown)
            }
            false
        }
        button1Mul.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button1Mul.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button1Mul.startAnimation(scaleDown)
            }
            false
        }
        button2Mul.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button2Mul.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button2Mul.startAnimation(scaleDown)
            }
            false
        }
        button3Mul.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                button3Mul.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                button3Mul.startAnimation(scaleDown)
            }
            false
        }
        startQuizButtonMul.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                startQuizButtonMul.startAnimation(scaleUp)
            } else if (event.action == MotionEvent.ACTION_UP) {
                startQuizButtonMul.startAnimation(scaleDown)
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

        //Activity-Multiplication
        startQuizButtonMul.setOnClickListener {
            constraintChildMul.visibility = View.VISIBLE
            startQuizButtonMul.visibility = View.GONE
            MenuBtn.visibility = View.GONE
            scoreMul = 0
            totalAttemptMul = 0
            statusTextViewMul.text = "0/0"
            newQuestionMul()
            countDownTimerMul = object : CountDownTimer(60200, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val one = millisUntilFinished.toInt()
                    val two = 1000
                    timerTextViewMul.text = ((one / two).toString() + "s")
                }


                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    constraintChildMul.visibility = View.GONE
                    startQuizButtonMul.visibility = View.VISIBLE
                    resultTextViewMul.visibility = View.VISIBLE
                    MenuBtn.visibility = View.VISIBLE
                    resultTextViewMul.text = "Time Up ! Your accuracy is $percentageMul"
                    startQuizButtonMul.text = "Play Again"
                }
            }.start()
        }//End Activity


    }
}