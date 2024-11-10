package com.example.airlines

import Beans.Path
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PathViewHolder(view: View): RecyclerView.ViewHolder(view){
    val pathText = view.findViewById<TextView>(R.id.txtCard)
    val cardId = view.findViewById<TextView>(R.id.txtCardId)
    val cost = view.findViewById<TextView>(R.id.txtCost)

    fun render(pathList: String, id: Int, cost: Double? = null){
        pathText.text = pathList
        cardId.text = id.toString()

        if(cost != null){
            this.cost.text = "$" + cost.toString()
        }else {
            // random float
            this.cost.text = "$" + (100..1500).random().toString()
        }
    }
}

class PrimViewHolder(view: View): RecyclerView.ViewHolder(view){
    val pathText = view.findViewById<TextView>(R.id.txtCard2)
    val cardId = view.findViewById<TextView>(R.id.txtCardId2)
    val cost = view.findViewById<TextView>(R.id.txtCost2)

    fun render(pathList: String, id: Int, cost: Double? = null){
        pathText.text = pathList
        cardId.text = id.toString()

        if(cost != null){
            this.cost.text = "$" + cost.toString()
        }else {
            // random float
            this.cost.text = "$" + (100..1500).random().toString()
        }
    }
}