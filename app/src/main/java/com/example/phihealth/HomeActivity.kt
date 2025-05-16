package com.example.phihealth

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Misal kamu punya tombol logout di layout dengan id logoutButton
        val logoutBtn = findViewById<ImageButton>(R.id.logout_button)
        logoutBtn.setOnClickListener {
            val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
            sharedPref.edit().clear().apply()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val historyBtn = findViewById<ImageButton>(R.id.history_button)
        historyBtn.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}
