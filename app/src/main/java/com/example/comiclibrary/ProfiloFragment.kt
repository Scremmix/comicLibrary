package com.example.comiclibrary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 * Use the [ProfiloFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfiloFragment() : Fragment() {
    private lateinit var userID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_profilo, container, false)
        userID = requireArguments().getString("userEmail").toString()
        val dbManager = DatabaseManager(this.context)
        try {
            dbManager.open()
        }catch (_: Exception)
        {}
        val userProp = dbManager.userIsAdmin(userID)
        val textViewTest: TextView = view.findViewById(R.id.textViewUserTest)
        textViewTest.text = "Benvenuto utente "+userID+" userAdmin= "+userProp.toString()
        return view
    }
}