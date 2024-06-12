package com.example.comiclibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UtentiAdapter (private val dataSet: List<RecordUtente>) :
        RecyclerView.Adapter<UtentiAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var emailLabel: TextView = view.findViewById(R.id.emailUtenteLista)
            var passwordLabel: TextView = view.findViewById(R.id.passwordUtenteLista)
            var adminLabel: TextView = view.findViewById(R.id.adminValueUtentiLista)
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.lista_utenti, viewGroup, false)

            return ViewHolder(view)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.emailLabel.text=dataSet[position].email
        holder.passwordLabel.text=dataSet[position].password
        holder.adminLabel.text= if(dataSet[position].admin)
            {holder.itemView.context.getString(R.string.admin_yes_profilo)}
            else{holder.itemView.context.getString(R.string.admin_no_profilo)}
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }


}