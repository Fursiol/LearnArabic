package com.learnarabic.tools


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learnarabic.R

class CustomAdapter(private val aList: List<ItemsViewModel>) :RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = aList[position]

        holder.textViewInitial.text = itemsViewModel.textInitial.toString()
        holder.textViewIsolated.text = itemsViewModel.textIsolated.toString()
        holder.textViewMedial.text = itemsViewModel.textMedial.toString()
        holder.textViewFinal.text = itemsViewModel.textFinal.toString()
        holder.textViewName.text = itemsViewModel.textName

    }

    override fun getItemCount(): Int {
        return aList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var textViewIsolated: TextView = itemView.findViewById(R.id.textViewIsolated)
        var textViewInitial: TextView = itemView.findViewById(R.id.textViewInitial)
        var textViewMedial: TextView = itemView.findViewById(R.id.textViewMedial)
        var textViewFinal: TextView = itemView.findViewById(R.id.textViewFinal)
        var textViewName: TextView = itemView.findViewById(R.id.textViewName)
    }
}