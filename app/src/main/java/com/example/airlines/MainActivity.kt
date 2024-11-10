package com.example.airlines

import Beans.GlobalVariables
import Models.RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val service = RetrofitClient.placeHolder
        service.obtainAirlines().enqueue(
            object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    Log.i("amdrius", response.body().toString())
                    if (response.body() == null) {
                        Toast.makeText(
                            this@MainActivity,
                            "No se encontraron aerolíneas",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val airlines = response.body()!!
                        GlobalVariables.airlines = airlines
                        val intent = Intent(this@MainActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Log.e("amdrius", t.message.toString())
                    Toast.makeText(
                        this@MainActivity,
                        "Error al obtener las aerolíneas",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        /*
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            // Navegar a otra Activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Opcional, si no quieres que el usuario pueda volver atrás
        }, 3000)*/
    }
}