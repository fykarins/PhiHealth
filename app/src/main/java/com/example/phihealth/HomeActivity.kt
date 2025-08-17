package com.example.phihealth

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class HomeActivity : AppCompatActivity() {

    private lateinit var textWeight: TextView
    private lateinit var textBP: TextView
    private lateinit var textPulse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Tombol logout
        val logoutBtn = findViewById<ImageButton>(R.id.logout_button)
        logoutBtn.setOnClickListener {
            val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
            sharedPref.edit().clear().apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Tombol history
        val historyBtn = findViewById<ImageButton>(R.id.history_button)
        historyBtn.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // Inisialisasi TextView
        textWeight = findViewById(R.id.text_weight)
        textBP = findViewById(R.id.text_bp)
        textPulse = findViewById(R.id.text_pulse)

        // Ambil data dari ESP32
        ambilDataESP32()
    }

    private fun ambilDataESP32() {
        val urlESP = "http://192.168.137.131/"

        Thread {
            try {
                val url = URL(urlESP)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"

                val response = conn.inputStream.bufferedReader().readText()
                android.util.Log.d("ESP32_DATA", "Response: $response")

                runOnUiThread {
                    parseAndDisplayData(response)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                android.util.Log.e("ESP32_ERROR", "Failed to connect: ${e.message}") // ✅ Tambahkan error log
            }
        }.start()
    }

    private fun parseAndDisplayData(response: String) {
        try {
            val json = JSONObject(response)

            val wgt = json.optDouble("WGT", 0.0).toString()
            val sys = json.optInt("SYS", 0).toString()
            val dia = json.optInt("DIA", 0).toString()
            val pulse = json.optInt("PULSE", 0).toString()

            textWeight.text = wgt
            textBP.text = "$sys / $dia"
            textPulse.text = "Pulse: $pulse BPM"
        } catch (e: Exception) {
            e.printStackTrace()
            android.util.Log.e("JSON_PARSE", "Error parsing JSON: ${e.message}")
        }
    }
}