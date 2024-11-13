package com.example.airlines

import Beans.GlobalVariables
import Models.RetrofitClient
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val searchAirline = findViewById<EditText>(R.id.search_airline)
        val queryCountry = findViewById<EditText>(R.id.query_country)
        val queryRouteLimit = findViewById<EditText>(R.id.query_limit_routes)
        val queryDestinationsLimit = findViewById<EditText>(R.id.query_limit_destinations)
        val button = findViewById<Button>(R.id.btn_search_airline)
        container.layoutManager = LinearLayoutManager(applicationContext)
        container.adapter = AirlinesAdapter(airlines, isOrigin)

        // when the user changes the search airline, the adapter is updated
        searchAirline.addTextChangedListener {
            container.adapter = AirlinesAdapter(airlines.filter { it.contains(searchAirline.text.toString(), true) }, isOrigin)
        }

        button.setOnClickListener {
            queryAirlines(container, queryCountry, queryRouteLimit, queryDestinationsLimit, isOrigin)
        }
    }

    private fun queryAirlines(container: RecyclerView, queryCountry: EditText, queryRouteLimit: EditText, queryDestionationsLimit: EditText, isOrigin: Boolean) {
        val country = queryCountry.text.toString()
        val routeLimit = queryRouteLimit.text.toString()
        val destinationLimit = queryDestionationsLimit.text.toString()

        val service = RetrofitClient.placeHolder
        service.obtainAirlines(country, routeLimit, destinationLimit).enqueue(object :
            Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful){
                    GlobalVariables.airlines = response.body()!!
                    container.adapter = AirlinesAdapter(GlobalVariables.airlines, isOrigin)
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("AirlinesActivity", "Error: ${t.message}")
            }
        })
    }
}