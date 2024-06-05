package com.example.comiclibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
    public fun amministratoreTrigg(view: View, esito:Boolean)
    {
        val emailBoxRegister= findViewById<EditText>(R.id.emailBoxRegister).text.toString().lowercase()
        val passwordFirstBoxRegister= findViewById<EditText>(R.id.passwordFirstBoxRegister).text.toString()
        val passwordSecondBoxRegister= findViewById<EditText>(R.id.passwordSecondBoxRegister).text.toString()
        val amministratoreCheckBox=findViewById<CheckBox>(R.id.checkBox_amministratore)
        if(esito)
        {
            val registerResult = registerProcedure(emailBoxRegister,passwordFirstBoxRegister,passwordSecondBoxRegister,true)
            Toast.makeText(this, registerResult, Toast.LENGTH_SHORT).show()
            if(registerResult.equals(getString(R.string.successRegister))) {
                finish() }
        }else{
            Toast.makeText(this, "Non si possiedono i permessi da amministratore", Toast.LENGTH_SHORT).show()
            amministratoreCheckBox.isChecked=false
        }
    }
    fun registerRun(view: View)
    {
        //Toast.makeText(this, "Hai premuto sul bottone di register!", Toast.LENGTH_SHORT).show() può tornare utile
        val emailBoxRegister= findViewById<EditText>(R.id.emailBoxRegister).text.toString().lowercase()
        val passwordFirstBoxRegister= findViewById<EditText>(R.id.passwordFirstBoxRegister).text.toString()
        val passwordSecondBoxRegister= findViewById<EditText>(R.id.passwordSecondBoxRegister).text.toString()
        val amministratoreCheckBox=findViewById<CheckBox>(R.id.checkBox_amministratore).isChecked
        if(amministratoreCheckBox)
        {
            val showPopUP=FragmentAmministratore()
            showPopUP.show((this as AppCompatActivity).supportFragmentManager,"amministratore_popUp")
        }else{
            val registerResult = registerProcedure(emailBoxRegister,passwordFirstBoxRegister,passwordSecondBoxRegister,false)
            Toast.makeText(this, registerResult, Toast.LENGTH_SHORT).show()
            if(registerResult.equals(getString(R.string.successRegister))) {
                finish()
            }
        }
    }

    fun validEmail(email:String):Boolean
    {
        val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
    fun registerProcedure(email:String, pw1:String, pw2:String, amministratore:Boolean):String{
        val dbManager = DatabaseManager(this)
        try {
            dbManager.open()
        }catch (_:Exception)
        {return getString(R.string.dbError)}

        if(!pw1.equals(pw2))
        {
            return getString(R.string.pwNotMatchingRegister)
        }else if (!validEmail(email))
        {
            return getString(R.string.emailPatternMismatch)
        }else{
            val success = dbManager.insertNewUser(email, pw1,amministratore)
            if(success)
                return getString(R.string.successRegister)
            else
                return getString(R.string.dbError)
        }
    }
    fun toLogin(view: View)
    {
        finish()
    }
}