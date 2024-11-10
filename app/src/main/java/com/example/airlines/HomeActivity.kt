package com.example.airlines

import Beans.GlobalVariables
import Beans.Path
import Beans.Prim
import Models.RetrofitClient
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class HomeActivity : AppCompatActivity() {
    private lateinit var source: LinearLayout
    private lateinit var destination: LinearLayout
    private lateinit var txtAirportOrigin: TextView
    private lateinit var txtAirportDestination: TextView
    private lateinit var swLowestCost: Switch
    private lateinit var txtExitDate: TextView
    private lateinit var txtArriveDate: TextView
    private lateinit var txtResults: TextView
    private lateinit var cardOneResult: CardView


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
        findViewById<TextView>(R.id.tv_user).text = GlobalVariables.username

        val btn = findViewById<TextView>(R.id.btn_search)
        val container = findViewById<RecyclerView>(R.id.rv_routes)

        source = findViewById(R.id.ll_origin)
        destination = findViewById(R.id.ll_destination)
        txtAirportOrigin = findViewById(R.id.txtAirportOrigin)
        txtAirportDestination = findViewById(R.id.txtAirportDestination)
        swLowestCost = findViewById(R.id.sw_lowest_cost)
        txtExitDate = findViewById(R.id.txtExitDate)
        txtArriveDate = findViewById(R.id.txtArriveDate)
        txtResults = findViewById(R.id.tv_results)
        cardOneResult = findViewById(R.id.one_result)

        if(GlobalVariables.airlineOrigin == "") {
            txtAirportOrigin.text = "Aeropuerto de origen"
        } else {
            txtAirportOrigin.text = GlobalVariables.airlineOrigin
        }

        if(GlobalVariables.airlineDestination == "") {
            txtAirportDestination.text = "Aeropuerto de destino"
        } else {
            txtAirportDestination.text = GlobalVariables.airlineDestination
        }


        btn.setOnClickListener {
            txtResults.text = ""
            container.visibility = RecyclerView.VISIBLE
            txtResults.visibility = TextView.VISIBLE
            if(swLowestCost.isChecked) {
                getLowestCost(container)
            } else {
                getMinimumPath(container)
            }
        }

        showAirlines()
        showCalendar()
    }

    private fun showCalendar() {
        txtExitDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay-${selectedMonth + 1}-$selectedYear"
                txtExitDate.text = date
            }, year, month, day)

            datePickerDialog.show()
        }

        txtArriveDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay-${selectedMonth + 1}-$selectedYear"
                txtArriveDate.text = date
            }, year, month, day)

            datePickerDialog.show()
        }
    }

    private fun showAirlines() {
        source.setOnClickListener {
            val intent = Intent(this, AirlinesActivity::class.java)
            intent.putExtra("isOrigin", true)
            startActivity(intent)
        }

        destination.setOnClickListener {
            val intent = Intent(this, AirlinesActivity::class.java)
            intent.putExtra("isOrigin", false)
            startActivity(intent)
        }
    }

    private fun getMinimumPath(container: RecyclerView) {

        val service = RetrofitClient.placeHolder
        service.obtainMinimumPath(txtAirportOrigin.text.toString(),
            txtAirportDestination.text.toString()
        )
            .enqueue(
                object : Callback<List<List<String>>> {
                override fun onResponse(
                    call: Call<List<List<String>>>,
                    response: Response<List<List<String>>>
                ) {
                    Log.i("amdrius", response.body().toString())
                    if (response.body() == null) {
                        runOnUiThread {
                            Toast.makeText(applicationContext, "No se hallaron rutas", Toast.LENGTH_SHORT).show()
                        }
                        txtResults.text = "No se encontraron resultados"

                        return
                    }
                    val paths = response.body()

                    container.layoutManager = LinearLayoutManager(applicationContext)
                    container.adapter = Adapter(paths!!)
                    txtResults.text = "Se encontraron ${paths.size} resultados"

                }

                override fun onFailure(call: Call<List<List<String>>>, t: Throwable) {
                    Log.e("amdrius", t.message.toString())
                    // show a toast
                    runOnUiThread {
                        Toast.makeText(applicationContext, "No se hallaron rutas", Toast.LENGTH_SHORT).show()
                    }

                    txtResults.text = "No se encontraron resultados"


                }
            })

    }

    private fun getLowestCost(container: RecyclerView) {
        val service = RetrofitClient.placeHolder
        service.obtainLowestCost(txtAirportOrigin.text.toString(),
            txtAirportDestination.text.toString()
        )
            .enqueue(
                object : Callback<Prim> {
                override fun onResponse(
                    call: Call<Prim>,
                    response: Response<Prim>
                ) {
                    Log.i("amdrius", response.body().toString())
                    if (response.body() == null) {
                        runOnUiThread {
                            Toast.makeText(applicationContext, "No se hallaron rutas", Toast.LENGTH_SHORT).show()
                        }
                        txtResults.text = "No se encontraron resultados"
                        return
                    }
                    val response = response.body()

                    cardOneResult.visibility = CardView.VISIBLE
                    container.visibility = RecyclerView.GONE

                    val pathText = findViewById<TextView>(R.id.txtCard2)
                    val cardId = findViewById<TextView>(R.id.txtCardId2)
                    val cost = findViewById<TextView>(R.id.txtCost2)

                    pathText.text = response!!.path.joinToString(" → ")
                    cardId.text = "1"
                    cost.text = "$" + response!!.cost.toString()

                    txtResults.text = "Se encontró 1 resultado"

                }

                override fun onFailure(call: Call<Prim>, t: Throwable) {
                    Log.e("amdrius", t.message.toString())
                    // show a toast
                    runOnUiThread {
                        Toast.makeText(applicationContext, "No se hallaron rutas", Toast.LENGTH_SHORT).show()
                    }

                }
            })
    }
}