package com.example.phihealth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}