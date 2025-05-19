package com.app.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.quizapp.databinding.ActivityMainBinding
import com.app.quizapp.databinding.ActivityMediumBinding

class MediumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMediumBinding
    private var userName: String? = null

    private val question =  arrayOf("What method is used to start a new activity?","Which layout allows positioning of child views relative to each other?",
        "How do you get a reference to a TextView with ID tvScore?","What does onCreate() do in an Activity?")

    private val option = arrayOf(arrayOf("startNewActivity()","launchActivity()","startActivity(Intent)","createActivity()"),
        arrayOf("LinearLayout","FrameLayout","ConstraintLayout","RelativeLayout"),
        arrayOf("TextView tv = findViewById(tvScore)","val tv = findViewById<TextView>(R.id.tvScore)","find<TextView>(R.id.tvScore)","tv = getView(R.id.tvScore)"),
        arrayOf("Handles screen rotations","Inflates the view and initializes the Activity","Deletes the layout","Starts a background service"))

    private val correctAnswers = arrayOf(2,2,1,1)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra("USER_NAME")

        displayQuestion()

        binding.optionA.setOnClickListener {
            checkAnswer(0)
        }
        binding.optionB.setOnClickListener {
            checkAnswer(1)
        }
        binding.optionC.setOnClickListener {
            checkAnswer(2)
        }
        binding.optionD.setOnClickListener {
            checkAnswer(3)
        }

    }

    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0-> binding.optionA.setBackgroundColor(ContextCompat.getColor(this, R.color.correctButton))
            1-> binding.optionB.setBackgroundColor(ContextCompat.getColor(this,R.color.correctButton))
            2-> binding.optionC.setBackgroundColor(ContextCompat.getColor(this,R.color.correctButton))
            3-> binding.optionD.setBackgroundColor(ContextCompat.getColor(this,R.color.correctButton))


        }

    }

    private fun wrongButtonColor(buttonIndex: Int){

        when(buttonIndex){
            0-> binding.optionA.setBackgroundColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_error))
            1-> binding.optionB.setBackgroundColor(ContextCompat.getColor(this,com.google.android.material.R.color.design_default_color_error))
            2-> binding.optionC.setBackgroundColor(ContextCompat.getColor(this,com.google.android.material.R.color.design_default_color_error))
            3-> binding.optionD.setBackgroundColor(ContextCompat.getColor(this,com.google.android.material.R.color.design_default_color_error))
        }
    }

    private fun restButtonColors(){
        binding.optionA.setBackgroundColor(ContextCompat.getColor(this,R.color.colorSecondary))
        binding.optionB.setBackgroundColor(ContextCompat.getColor(this,R.color.colorSecondary))
        binding.optionC.setBackgroundColor(ContextCompat.getColor(this,R.color.colorSecondary))
        binding.optionD.setBackgroundColor(ContextCompat.getColor(this,R.color.colorSecondary))

    }

    private fun displayQuestion(){
        binding.questionText.text = question[currentQuestionIndex]
        binding.optionA.text = option[currentQuestionIndex][0]
        binding.optionB.text = option[currentQuestionIndex][1]
        binding.optionC.text = option[currentQuestionIndex][2]
        binding.optionD.text = option[currentQuestionIndex][3]
        restButtonColors()

    }


    private fun checkAnswer(selectAnswerIndex : Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if(selectAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectAnswerIndex)
        }
        else{
            wrongButtonColor(selectAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        if(currentQuestionIndex < question.size-1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()},1000)
        }
        else {

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL_QUESTIONS", question.size)
            startActivity(intent)
            finish()
        }


    }
}