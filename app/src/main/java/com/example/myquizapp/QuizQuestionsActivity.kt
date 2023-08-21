package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.AdaptiveIconDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mQuestionList : ArrayList<Questions>? = null
    private var selectedOptionPosition :Int = 0



    private var mUserName :String? = null
    private var mCorrectAnswers :Int =0


    private var progressBar: ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView ? = null

    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null
    private var btnSubmit : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)


        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_questions)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne= findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)


        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)


         mQuestionList = Constants.getQuestions()

        if(mCurrentPosition==mQuestionList!!.size){
            btnSubmit?.text = "FINISH"

        }else{
            btnSubmit?.text = "SUBMIT"
        }


        setQuestion()

    }

    private fun setQuestion() {
        defaultOptionView()

        val question: Questions = mQuestionList!![mCurrentPosition - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
    }


    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }

        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv : TextView ,selectedOptionNumber : Int){
        defaultOptionView()
        selectedOptionPosition = selectedOptionNumber
        tv.setTextColor(Color.parseColor("#2D2929"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
            this,
            R.drawable.default_option_background_bg
        )
    }


    private val handler = Handler()

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it,1)

                }
            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it,2)

                }
            }

            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it,3)

                }
            }

            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it,4)

                }
            }

            R.id.btn_submit -> {
                if (selectedOptionPosition == 0) {
                    // The user didn't select any option, show Toast or handle appropriately
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)

                    if (question!!.correctOption != selectedOptionPosition) {
                        answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctOption, R.drawable.correct_option_border_bg)

                    // Disable click events to prevent selecting another option before the next question.
                    disableOptionClick()
                    // Delay the transition to the next question after 3 seconds.
                    handler.postDelayed({
                        mCurrentPosition++
                        if (mCurrentPosition <= mQuestionList!!.size) {
                            setQuestion()
                            // Reset the UI after showing the correct answer for 0.5 seconds.
                            resetOptionView()
                        } else {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }, 500)
                    selectedOptionPosition = 0
                }
            }
        }
    }


    private fun disableOptionClick() {
        // Disable click events on options while showing the correct answer.
        tvOptionOne?.isEnabled = false
        tvOptionTwo?.isEnabled = false
        tvOptionThree?.isEnabled = false
        tvOptionFour?.isEnabled = false
    }


    private fun resetOptionView() {
        // Reset the option views to the default state.
        defaultOptionView()

        // Enable click events on options.
        tvOptionOne?.isEnabled = true
        tvOptionTwo?.isEnabled = true
        tvOptionThree?.isEnabled = true
        tvOptionFour?.isEnabled = true

        // Update the button text condition.
        if (mCurrentPosition == mQuestionList!!.size) {
            btnSubmit?.text = "FINISH"
        } else {
            btnSubmit?.text = "SUBMIT"
        }
    }


    private fun answerView(answer : Int , drawableView: Int){
        when (answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
        }

    }
}