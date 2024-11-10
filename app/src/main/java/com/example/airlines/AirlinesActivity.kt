package com.example.airlines

import Beans.GlobalVariables
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AirlinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_airlines)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // get the extra
        val isOrigin = intent.getBooleanExtra("isOrigin", false)

        val airlines = GlobalVariables.airlines
        val container = findViewById<RecyclerView>(R.id.rv_airlines)
        container.layoutManager = LinearLayoutManager(applicationContext)
        container.adapter = AirlinesAdapter(airlines, isOrigin)
    }
}