package com.example.assign7

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class NumberAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private val numberList = mutableListOf<Int>()

    override fun getItemCount(): Int = numberList.size

    override fun createFragment(position: Int): Fragment {
        val numberFrag = NumberFragment()
        val bundle = Bundle()

        bundle.putInt("number", numberList[position])
        numberFrag.arguments = bundle

        return numberFrag
    }

    fun addNumber() {
        for(i in 1..10) numberList.add(i)
        notifyDataSetChanged()
    }
}