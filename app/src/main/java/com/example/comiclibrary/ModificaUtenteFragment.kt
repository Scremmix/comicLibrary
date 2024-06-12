package com.example.comiclibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 * Use the [ModificaUtenteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModificaUtenteFragment(var caller:UtentiAdapter, var email:String) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_modifica_utente, container, false)
        var button = view.findViewById<Button>(R.id.confirmChangesEditUser)
        button.setOnClickListener { onConfirm(view) }
        return view
    }

    fun onConfirm(view:View)
    {
        caller.editUserCallBack(
            email,
            view.findViewById<TextInputEditText>(R.id.inserimentoNuovaPassword).text.toString(),
            view.findViewById<CheckBox>(R.id.modificaUtenteAdminCheck).isChecked
        )
        dismiss()
    }

}