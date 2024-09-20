package com.example.airlines

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        android.os.Handler(Looper.getMainLooper()).postDelayed({
            // Navegar a otra Activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Opcional, si no quieres que el usuario pueda volver atrás
        }, 3000)
    }
}