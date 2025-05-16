package com.example.phihealth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.loginButton)
        val registerLink: TextView = findViewById(R.id.registerLink)

        loginButton.setOnClickListener {
            // Simpan status login
            val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
            sharedPref.edit().putBoolean("is_logged_in", true).apply()

            // Redirect ke HomeActivity
            startActivity(Intent(this, HomeActivity::class.java))
            finish() // Tutup LoginActivity
        }

        registerLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}