package com.example.bcsdassign4

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

class ChangeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_activity)
        val numberText: TextView = findViewById(R.id.number_text2)
        val explainText: TextView = findViewById(R.id.explain_text)

        val random = Random
        val intent = Intent(this, MainActivity::class.java).apply {
            val value = intent.getIntExtra("count", -1)
            explainText.text="Here is a random number between 0 and $value"
            val count = random.nextInt(value + 1)
            numberText.text = count.toString()
            putExtra("count", count)
        }
        setResult(RESULT_OK, intent)
    }
}