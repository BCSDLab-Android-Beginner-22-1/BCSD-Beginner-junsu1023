package com.example.bcsdassign4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class FragmentActivity : Fragment() {
    lateinit var countData: Count

    override fun onAttach(context: Context) {
        super.onAttach(context)
        countData = context as Count
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_activity, container, false)
        val numberTextView: TextView = rootView.findViewById(R.id.number_text2)
        val explainTextView: TextView = rootView.findViewById(R.id.explain_text)
        val count = requireArguments().getInt("count", 0)
        val randomNum = Random.nextInt(count + 1)

        explainTextView.text = getString(R.string.between_num, count)
        numberTextView.text = randomNum.toString()
        sendCount(randomNum)
        return rootView
    }

    private fun sendCount(randomNum: Int) {
        countData.count(randomNum)
    }
}