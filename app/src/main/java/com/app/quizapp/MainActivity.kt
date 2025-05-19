package com.app.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userName: String? = null

    private val question =  arrayOf("What keyword is used to declare a variable in Kotlin that can’t be reassigned?",
        "What is the correct way to define a function in Kotlin?","What does the Elvis operator (?:) do in Kotlin?","What is the type of a string in Kotlin?")

    private val option = arrayOf(arrayOf("var","val","let","const"),
        arrayOf("function sum(a:Int, b:Int) = a + b","fun sum(a:Int, b:Int): Int = a + b","def sum(a:Int, b:Int): Int","func sum(a:Int, b:Int): Int"),
        arrayOf("Compares two numbers","Returns the right-hand value if the left is null","Creates a new class", "Defines a lambda"),
        arrayOf("str","String","Text","string"))

    private val correctAnswers = arrayOf(1,1,1,1)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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