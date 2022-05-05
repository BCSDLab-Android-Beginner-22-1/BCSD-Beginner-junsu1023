package com.example.bcsdassign4

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class ChangeActivity : AppCompatActivity(), Count {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_activity)

        val fragment: Fragment = FragmentActivity()
        val count = intent.getIntExtra("count", 0)
        val bundle = Bundle().apply { putInt("count", count) }
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
    }

    override fun count(randomNum: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("count", randomNum)
        setResult(RESULT_OK, intent)
    }
}