package com.example.comiclibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment


/**
 * A simple [Fragment] subclass.
 * Use the [EliminaUtenteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminaUtenteFragment(var caller:UtentiAdapter, var email:String): DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_elimina_utente, container, false)
        view.findViewById<TextView>(R.id.emailToDelete).text = email
        val confirmButton=view.findViewById<Button>(R.id.deleteUserConfirmButton)
        confirmButton.setOnClickListener { onConfirm() }
        return view
    }

    fun onConfirm()
    {
        caller.deleteUserCallBack(email)
        dismiss()
    }

}