package com.example.comiclibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment


class FragmentAmministratore : DialogFragment()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun onControllo(view: View)
    {
        val codice_inserito=view.findViewById<EditText>(R.id.inserimento_codice_verifica).text.toString()
        if(codice_inserito.equals(resources.getString(R.string.admin_code)))
        {
            (activity as RegisterActivity?)!!.amministratoreTrigg(view,true)
        }else{
            Toast.makeText(this.context, "codice errato", Toast.LENGTH_SHORT).show()
            (activity as RegisterActivity?)!!.amministratoreTrigg(view,false)
        }
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amministratore, container, false)
    }

    fun onControlloAmministratore(view: View) {val codice_inserito=view.findViewById<EditText>(R.id.inserimento_codice_verifica).text.toString()
        if(codice_inserito.equals(resources.getString(R.string.admin_code)))
        {
            (activity as RegisterActivity?)!!.amministratoreTrigg(view,true)
        }else{
            Toast.makeText(this.context, "codice errato", Toast.LENGTH_SHORT).show()
            (activity as RegisterActivity?)!!.amministratoreTrigg(view,false)
        }
        dismiss()}

}