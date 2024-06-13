package com.example.comiclibrary

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.comiclibrary.databinding.ActivityHomePageBinding



class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var userID: String
    private var userAdmin: Boolean=false
    fun onLogout(view: View){
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        val dbManager=DatabaseManager(this)
        dbManager.open()
        userID = extras?.getString("userEmail").toString()
        userAdmin=dbManager.userIsAdmin(userID)
        binding=ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cambiaFragment(if (userAdmin)
            {HomeFragmentAdmin(userID)}else{HomeFragment(userID)})
        binding.menuNavBar.setOnItemSelectedListener {
            var fragment = when(it.itemId)
            {
                R.id.home_nav_bar-> {
                    if (userAdmin)
                    {
                        HomeFragmentAdmin(userID)
                    }else{
                        HomeFragment(userID)
                    }
                }
                R.id.search_nav_bar-> RicercaFragment()
                R.id.profile_nav_bar-> ProfiloFragment()
                else -> {HomeFragment(userID)}
            }
            val bundle = Bundle()
            bundle.putString("userEmail", userID)
            fragment.setArguments(bundle)
            cambiaFragment(fragment)
            true
        }
    }
    private fun cambiaFragment(fragment:Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.homePage_fragment_container, fragment)
        fragmentTransaction.commit()
    }
}