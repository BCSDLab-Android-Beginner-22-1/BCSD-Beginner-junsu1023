package com.example.assign7

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btmNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)

        val rainbowAdapter = RainbowAdapter(this)
        rainbowAdapter.addColor()

        val numberAdapter = NumberAdapter(this)
        numberAdapter.addNumber()

        val alphabetAdapter = AlphabetAdapter(this)
        alphabetAdapter.addAlphabet()

        viewPager.adapter = rainbowAdapter

        btmNavigation.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.rainbow_button -> {
                        viewPager.adapter = rainbowAdapter
                    }
                    R.id.number_button -> {
                        viewPager.adapter = numberAdapter
                    }
                    R.id.alphabet_button -> {
                        viewPager.adapter = alphabetAdapter
                    }
                }
                true
            }
        }
    }
}