package com.example.comiclibrary

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Use the [RicercaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RicercaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_ricerca, container, false)
        val ricercaBox=view.findViewById<SearchView>(R.id.barra_ricerca)
        val db_manager= DatabaseManager(this.context)
        db_manager.open()
        ricercaBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.equals(""))
                {
                    val risultato= db_manager.ricercaFumettoPerTitolo(newText)
                    mostraFumetti(risultato, view)
                    risultato.close()
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.equals(""))
                {
                    val risultato= db_manager.ricercaFumettoPerTitolo(query)
                    mostraFumetti(risultato, view)
                    risultato.close()
                }
                return true
            }

        })
        return view
    }
    fun mostraFumetti(fumetti: Cursor, view: View) {
        val recyclerView= view.findViewById<RecyclerView>(R.id.recycler_ricerca)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val fumettiList= mutableListOf<RecordFumetto>()
        if(fumetti.count>0) {
            do {
                val copertina = if (fumetti.isNull(0)) null else fumetti.getBlob(0)
                fumettiList.add(
                    RecordFumetto(
                        copertina,
                        fumetti.getString(1),
                        fumetti.getString(2),
                        fumetti.getInt(3)
                    )
                )
            } while (fumetti.moveToNext())
        }else{
            Toast.makeText(view.context, view.context.getString(R.string.noFumettoFound), Toast.LENGTH_SHORT).show()
        }
        val adapter= FumettiAdapter(fumettiList)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_ricerca)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val fumettiList= mutableListOf<RecordFumetto>()
        val adapter= FumettiAdapter(fumettiList)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }
}