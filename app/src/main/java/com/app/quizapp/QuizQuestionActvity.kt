package com.app.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.quizapp.databinding.ActivityLoginBinding
import com.app.quizapp.databinding.ActivityQuizQuestionActvityBinding

class QuizQuestionActvity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizQuestionActvityBinding
    private var userName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra("USER_NAME")

        setupClickListner()

    }

    private fun setupClickListner(){


        binding.easyButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
            finish()
        }

        binding.mediumButton.setOnClickListener {
            val intent = Intent(this, MediumActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
            finish()
        }

        binding.hardButton.setOnClickListener {
            val intent = Intent(this, HardActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
            finish()
        }
    }
}