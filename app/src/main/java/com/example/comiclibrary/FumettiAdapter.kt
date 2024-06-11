package com.example.comiclibrary

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FumettiAdapter(private val dataSet: List<RecordFumetto>) :
    RecyclerView.Adapter<FumettiAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var copertina: ImageView = view.findViewById(R.id.copertina_fumetto)
        var titolo: TextView = view.findViewById(R.id.titolo_fumetto)
        var serie: TextView= view.findViewById(R.id.serie_fumetto)
        var disponibilita: TextView = view.findViewById(R.id.disponibilita)

        init {
            // Define click listener for the ViewHolder's View
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.lista_fumetti, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val copertina = dataSet[position].copertina
        if(copertina!= null)
        {
            val bitmap = BitmapFactory.decodeByteArray(copertina, 0, copertina.size)
            viewHolder.copertina.setImageBitmap(bitmap)
        }else{
            viewHolder.copertina.setImageResource(R.drawable.icon)
        }

        viewHolder.titolo.text=dataSet[position].titolo
        viewHolder.serie.text=dataSet[position].serie
        val disponibilitaText = when(dataSet[position].disponibilita){
            0 -> viewHolder.itemView.context.getString(R.string.fumettoAvailableText)
            2 -> viewHolder.itemView.context.getString(R.string.fumettoNotAvailableText)
            else -> viewHolder.itemView.context.getString(R.string.fumettoBookedText)
        }
        viewHolder.disponibilita.text=disponibilitaText
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}