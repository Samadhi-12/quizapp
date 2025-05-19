package com.app.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.quizapp.databinding.ActivityLoginBinding

class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListner()



    }

    private fun setupClickListner(){

        binding.startButton.setOnClickListener {
            val name = binding.startName.text.toString().trim()

            if(name.isEmpty()){
                Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show()
                binding.startName.text?.clear()
            }

            else{
                val intent = Intent(this, QuizQuestionActvity::class.java)
                intent.putExtra("USER_NAME", name)
                startActivity(intent)
                finish()


            }
        }

    }
}