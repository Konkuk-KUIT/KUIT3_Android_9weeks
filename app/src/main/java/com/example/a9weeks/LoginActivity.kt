package com.example.a9weeks


import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.a9weeks.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        clickSignUp()

        setContentView(binding.root)

    }

    private fun clickSignUp() {
        binding.btnSignup.setOnClickListener {
            var intent=Intent(applicationContext,SignupActivity::class.java)
            startActivity(intent)
        }
    }
}