package com.example.comiclibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
    fun registerRun(view: View)
    {
        Toast.makeText(this, "Hai premuto sul bottone di register!", Toast.LENGTH_SHORT).show()
    }

    fun toLogin(view: View)
    {
        finish()
    }
}