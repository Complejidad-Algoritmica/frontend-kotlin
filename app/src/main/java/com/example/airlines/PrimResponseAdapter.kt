package com.example.airlines

import Beans.Prim
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PrimResponseAdapter(val response: Prim): RecyclerView.Adapter<PrimViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrimViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PrimViewHolder(layoutInflater.inflate(R.id.one_result, parent, false))
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: PrimViewHolder, position: Int) {
        Log.i("amdrius", response.path.toString())
        val textStyling = response.path.joinToString(" → ")
        holder.render(textStyling, 1, response.cost)

    }
}