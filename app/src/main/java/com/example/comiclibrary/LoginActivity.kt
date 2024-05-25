package com.example.comiclibrary

import android.content.Intent
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
        var userEmail = findViewById<EditText>(R.id.emailBoxLogin).text.toString().lowercase()
        val esito_login= loginProcedure(userEmail,
            findViewById<EditText>(R.id.passwordLogin).text.toString())
        Toast.makeText(this, esito_login,
            Toast.LENGTH_SHORT).show()
        if(esito_login.equals(getString(R.string.successLogin)))
        {
            val intent=Intent(this,HomePageActivity::class.java)
            intent.putExtra("userEmail", userEmail)
            startActivity(intent)
            finish()
        }

    }

    fun loginProcedure(email:String, password:String):String{

        val dbManager = DatabaseManager(this)
        try {
            dbManager.open()
        }catch (e: Exception)
        {}
        val cursor = dbManager.getAllUsers()
        do{
            val qUtenteIndex = cursor.getColumnIndex(DatabaseHelper.MAIL_UTENTE)
            if(qUtenteIndex>-1) {
                val q_utente = cursor.getString(qUtenteIndex)
                if(q_utente.equals(email))
                {
                    val qPasswordIndex = cursor.getColumnIndex(DatabaseHelper.PASSWORD_UTENTE)
                    if(qPasswordIndex>-1)
                    {
                        val q_password = cursor.getString(qPasswordIndex)
                        if(q_password.equals(password)){
                            return getString(R.string.successLogin)
                        }
                    }
                    else
                        return getString(R.string.credentialsNotFoundLogin)
                }
            }
            cursor.moveToNext()
        }while(cursor.position<(cursor.count))
        return getString(R.string.failedLogin)
    }

    fun toRegister(view: View)
    {
        startActivity(Intent(this,RegisterActivity::class.java))
    }
}