package com.example.comiclibrary

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun loginRun(view: View)
    {
        val dbManager = DatabaseManager(this)
        try {
            dbManager.open()
        }catch (e: Exception)
        {}
        val emailBoxLogin= findViewById<EditText>(R.id.emailBoxLogin).text.toString().toLowerCase()
        val passwordBoxLogin= findViewById<EditText>(R.id.passwordLogin).text.toString()
        val cursor = dbManager.fetch()
        do{
            val qUtenteIndex = cursor.getColumnIndex(DatabaseHelper.MAIL_UTENTE)
            if(qUtenteIndex>-1) {
                val q_utente = cursor.getString(qUtenteIndex)
                if(q_utente.equals(emailBoxLogin))
                {
                    val qPasswordIndex = cursor.getColumnIndex(DatabaseHelper.PASSWORD_UTENTE)
                    if(qPasswordIndex>-1)
                    {
                        val q_password = cursor.getString(qPasswordIndex)
                        if(q_password.equals(passwordBoxLogin)){
                            Toast.makeText(this, "Login eseguito con successo", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "Password errata", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this, "Errore nella query", Toast.LENGTH_SHORT).show()
            }
        }while(!cursor.isAfterLast)

        Toast.makeText(this, "Utente non trovato", Toast.LENGTH_SHORT).show()
    }

    fun toRegister(view: View)
    {
        startActivity(Intent(this,RegisterActivity::class.java))
    }
}