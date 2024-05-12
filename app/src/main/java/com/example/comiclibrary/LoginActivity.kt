package com.example.comiclibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun loginRun(view: View)
    {
        Toast.makeText(this, "Hai premuto sul bottone di login!", Toast.LENGTH_SHORT).show()
    }

    fun toRegister(view: View)
    {
        Toast.makeText(this, "Hai premuto sul bottone di register!", Toast.LENGTH_SHORT).show()
    }
}