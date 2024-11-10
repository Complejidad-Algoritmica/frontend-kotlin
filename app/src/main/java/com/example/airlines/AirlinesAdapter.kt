package com.example.airlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AirlinesAdapter(val airlines: List<String>, val isOrigin: Boolean): RecyclerView.Adapter<AirlinesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlinesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AirlinesViewHolder(layoutInflater.inflate(R.layout.airline_card, parent, false))
    }

    override fun getItemCount(): Int = airlines.size

    override fun onBindViewHolder(holder: AirlinesViewHolder, position: Int) {
        val item = airlines[position]
        holder.render(item, isOrigin)
    }
}