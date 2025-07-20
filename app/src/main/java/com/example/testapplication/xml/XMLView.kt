package com.example.testapplication.xml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class XMLMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNav()
        loadFragment(HomeFragment())
    }

    private fun setupBottomNav() {
        val nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> loadFragment(HomeFragment())
                R.id.statsFragment -> loadFragment(StatsFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
