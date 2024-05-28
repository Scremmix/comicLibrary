package com.example.comiclibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 * Use the [ProfiloFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfiloFragment() : Fragment() {
    private lateinit var userID: String
    private lateinit var pw:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun onLogout(view: View){
    (activity as HomePageActivity?)!!.onLogout(view)
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
        pw=dbManager.getUser_password(userID)
        view.findViewById<TextView>(R.id.email_profilo_layout).text=userID
        view.findViewById<TextInputEditText>(R.id.password_profilo_layout).setText(pw)
        view.findViewById<TextView>(R.id.amministratore_profilo_layout).text=userProp.toString()
        return view
    }
}