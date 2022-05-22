package com.example.assign7

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlphabetAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private val alphabetList = mutableListOf<Char>()

    override fun getItemCount(): Int = alphabetList.size

    override fun createFragment(position: Int): Fragment {
        val alphabetFrag = AlphabetFragment()
        val bundle = Bundle()

        bundle.putChar("alphabet", alphabetList[position])
        alphabetFrag.arguments = bundle

        return alphabetFrag
    }

    fun addAlphabet() {
        for(i in 'a' .. 'z') alphabetList.add(i)
        notifyDataSetChanged()
    }
}