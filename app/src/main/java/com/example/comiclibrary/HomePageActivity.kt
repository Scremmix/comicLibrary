package com.example.comiclibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.comiclibrary.databinding.ActivityHomePageBinding


class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var userID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        userID = extras?.getString("userEmail").toString()
        binding=ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cambiaFragment(HomeFragment())
        binding.menuNavBar.setOnItemSelectedListener {
            var fragment = when(it.itemId)
            {
                R.id.home_nav_bar-> HomeFragment()
                R.id.search_nav_bar-> RicercaFragment()
                R.id.profile_nav_bar-> ProfiloFragment()
                else -> {HomeFragment()}
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