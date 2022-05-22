package com.example.assign7

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RainbowAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private val colorList = mutableListOf<Int>()

    override fun getItemCount(): Int = colorList.size

    override fun createFragment(position: Int): Fragment {
        val rainbowFrag = RainbowFragment()
        val bundle = Bundle()

        bundle.putInt("color", colorList[position])
        rainbowFrag.arguments = bundle

        return rainbowFrag
    }

    fun addColor() {
        colorList.add(Color.parseColor("#FF0000"))
        colorList.add(Color.parseColor("#FFA500"))
        colorList.add(Color.parseColor("#FFFF00"))
        colorList.add(Color.parseColor("#008000"))
        colorList.add(Color.parseColor("#0000FF"))
        colorList.add(Color.parseColor("#4B0082"))
        colorList.add(Color.parseColor("#800080"))
        notifyDataSetChanged()
    }
}