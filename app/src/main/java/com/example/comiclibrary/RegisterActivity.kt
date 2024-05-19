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
        val registerResult = registerProcedure(emailBoxRegister,passwordFirstBoxRegister,passwordSecondBoxRegister)
        Toast.makeText(this, registerResult, Toast.LENGTH_SHORT).show()
        if(registerResult.equals(getString(R.string.successRegister))){
            finish()
        }
    }

    fun registerProcedure(email:String, pw1:String, pw2:String):String{
        val dbManager = DatabaseManager(this)
        try {
            dbManager.open()
        }catch (_:Exception)
        {return getString(R.string.dbError)}

        if(pw1.equals(pw2))
        {
            val success = dbManager.insert(email, pw1,false)
            if(success)
                return getString(R.string.successRegister)
            else
                return getString(R.string.dbError)
        }else
        {
            return getString(R.string.pwNotMatchingRegister)
        }
    }
    fun toLogin(view: View)
    {
        finish()
    }
}