package com.example.airlines

import Beans.GlobalVariables
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AirlinesViewHolder(view: View): RecyclerView.ViewHolder(view){
    var airline = view.findViewById<TextView>(R.id.txtCardAirline)

    fun render(text: String, isOrigin: Boolean) {
        airline.text = text

        itemView.setOnClickListener {
            if (isOrigin) {
                GlobalVariables.airlineOrigin = text
            } else {
                GlobalVariables.airlineDestination = text
            }

            val context = itemView.context
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}