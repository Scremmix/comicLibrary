package com.example.comiclibrary

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.comiclibrary.databinding.ActivityHomePageBinding


class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cambiaFragment(HomeFragment())

        binding.menuNavBar.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_nav_bar-> cambiaFragment(HomeFragment())
                R.id.search_nav_bar-> cambiaFragment(RicercaFragment())
                R.id.profile_nav_bar-> cambiaFragment(ProfiloFragment())
                else->{}
            }
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