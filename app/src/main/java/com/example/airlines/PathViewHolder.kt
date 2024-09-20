package com.example.airlines

import Beans.Path
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PathViewHolder(view: View): RecyclerView.ViewHolder(view){
    val pathText = view.findViewById<TextView>(R.id.txtCard)
    val cardId = view.findViewById<TextView>(R.id.txtCardId)

    fun render(pathList: String, id: Int) {
        pathText.text = pathList
        cardId.text = id.toString()
    }
}