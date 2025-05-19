package com.app.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from intent inside onCreate
        val userName = intent.getStringExtra("USER_NAME")
        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)

        // Set the text to your views using original IDs
        binding.usernameText.text = userName
        binding.scoreText.text = "Your Score is $score out of $totalQuestions"

        binding.finishButton.setOnClickListener {
            val intent = Intent(this, QuizQuestionActvity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
            finish()
        }
    }
}
