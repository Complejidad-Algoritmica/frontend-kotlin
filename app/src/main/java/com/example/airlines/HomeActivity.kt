package com.example.airlines

import Models.RetrofitClient
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibe el argumento enviado desde LoginActivity
        val userName = intent.getStringExtra("userName")

        findViewById<TextView>(R.id.tv_user).text = userName
        val btn = findViewById<TextView>(R.id.btn_search)
        btn.setOnClickListener {
            val source = findViewById<TextView>(R.id.et_origin).text.toString()
            val destination = findViewById<TextView>(R.id.et_destino).text.toString()

            getMinimumPath(source, destination)
        }

    }
    private fun getMinimumPath(source: String, destination: String) {



        val service = RetrofitClient.placeHolder
        service.obtainMinimumPath(source, destination)
            .enqueue(object: Callback<List<List<String>>>{
                override fun onResponse(
                    call: Call<List<List<String>>>,
                    response: Response<List<List<String>>>
                ) {
                    Log.i("amdrius", response.body().toString())
                }

                override fun onFailure(call: Call<List<List<String>>>, t: Throwable) {
                    Log.e("amdrius", t.message.toString())
                }
            })
    }
}