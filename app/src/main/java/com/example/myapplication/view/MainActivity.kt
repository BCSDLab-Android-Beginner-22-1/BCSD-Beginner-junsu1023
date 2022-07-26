package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import com.example.myapplication.fragment.FilmListFragment
import com.example.myapplication.fragment.PeopleListFragment
import com.example.myapplication.fragment.PlanetListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filmListFragment = FilmListFragment()
        val peopleListFragment = PeopleListFragment()
        val planetListFragment = PlanetListFragment()

        supportFragmentManager.beginTransaction().replace(R.id.container, filmListFragment).commit()

        val btmNav: BottomNavigationView = findViewById(R.id.bottom_nav)
        btmNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_films -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, filmListFragment).commit()
                    true
                }
                R.id.navigation_peoples -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, peopleListFragment).commit()
                    true
                }
                R.id.navigation_planets -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, planetListFragment).commit()
                    true
                }
                else -> false
            }
        }
    }
}
