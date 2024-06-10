package com.example.comiclibrary

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        ricercaBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.equals(""))
                {
                    val risultato= db_manager.ricercaFumetto(newText)
                    MostraFumetti(risultato, view)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

        })
        return inflater.inflate(R.layout.fragment_ricerca, container, false)

    }
    fun MostraFumetti(fumetti: Cursor, view: View) {
        val recyclerView= view.findViewById<RecyclerView>(R.id.recycler_ricerca)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val adapter= FumettiAdapter(context,fumetti)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView= view.findViewById<RecyclerView>(R.id.recycler_ricerca)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val adapter= FumettiAdapter(context, null)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }
}