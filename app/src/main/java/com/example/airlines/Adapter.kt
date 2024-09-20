package com.example.airlines

import Beans.Path
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(val pathList: List<List<String>>): RecyclerView.Adapter<PathViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PathViewHolder(layoutInflater.inflate(R.layout.path_card, parent, false))
    }

    override fun getItemCount(): Int = pathList.size

    override fun onBindViewHolder(holder: PathViewHolder, position: Int) {
        val item = pathList[position]
        val textStyling = item.joinToString(" → ")


        holder.render(textStyling, position+1)
    }
}