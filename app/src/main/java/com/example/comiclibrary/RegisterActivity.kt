package com.example.comiclibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
    fun registerRun(view: View)
    {
        //Toast.makeText(this, "Hai premuto sul bottone di register!", Toast.LENGTH_SHORT).show() può tornare utile
        val emailBoxRegister= findViewById<EditText>(R.id.emailBoxRegister).text.toString().toLowerCase()
        val passwordFirstBoxRegister= findViewById<EditText>(R.id.passwordFirstBoxRegister).text.toString()
        val passwordSecondBoxRegister= findViewById<EditText>(R.id.passwordSecondBoxRegister).text.toString()
        val dbManager = DatabaseManager(this)
        try {
            dbManager.open()
        }catch (e:Exception)
        {}

        if(passwordFirstBoxRegister.equals(passwordSecondBoxRegister))
        {
            val success = dbManager.insert(emailBoxRegister, passwordFirstBoxRegister,false)
            var toastText = ""
            if(success)
                toastText = "Registrazione effettuata con successo"
            else
                toastText = "Errore con il db"
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            finish()
        }else
        {
            Toast.makeText(this, "Le password non corrispondono", Toast.LENGTH_SHORT).show()
        }

    }

    fun toLogin(view: View)
    {
        finish()
    }
}