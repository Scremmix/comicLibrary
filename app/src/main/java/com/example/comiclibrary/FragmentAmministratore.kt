package com.example.comiclibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment


class FragmentAmministratore : DialogFragment()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_amministratore, container, false)
        view.findViewById<TextView>(R.id.bottone_conferma).setOnClickListener{
            onControlloAmministratore(view)
        }
        // Inflate the layout for this fragment
        return view
    }

    fun onControlloAmministratore(view: View) : Unit{
        val codice_inserito=view.findViewById<EditText>(R.id.inserimento_codice_verifica).text.toString()
        if(codice_inserito.equals(resources.getString(R.string.admin_code)))
        {
            (activity as RegisterActivity?)!!.amministratoreTrigg(view,true)
        }else{
            Toast.makeText(this.context, "codice errato", Toast.LENGTH_SHORT).show()
            (activity as RegisterActivity?)!!.amministratoreTrigg(view,false)
        }
        dismiss()
        return
    }

}