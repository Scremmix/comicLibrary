package com.example.comiclibrary

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragmentAdmin.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragmentAdmin : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_admin, container, false)
        val db_manager= DatabaseManager(this.context)
        db_manager.open()
        val risultato= db_manager.allUsers
        mostraUtenti(risultato, view)
        risultato.close()
        return view
    }
    fun mostraUtenti(utenti: Cursor, view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_utenti)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val utentiList= mutableListOf<RecordUtente>()
        do{
            utentiList.add(RecordUtente(utenti.getString(0),
                utenti.getString(1),
                utenti.getInt(2)==1
            ))
        }while(utenti.moveToNext())
        val adapter = UtentiAdapter(utentiList)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db_manager= DatabaseManager(this.context)
        db_manager.open()
        val risultato= db_manager.allUsers
        mostraUtenti(risultato, view)
        risultato.close()
    }

}