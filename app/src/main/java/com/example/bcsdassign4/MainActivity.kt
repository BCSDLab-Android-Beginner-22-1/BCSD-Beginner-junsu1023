package com.example.bcsdassign4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val random = Random
        var count = 0
        val toastButton: Button = findViewById(R.id.toast_button)
        toastButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "toast 메시지 버튼 클릭", Toast.LENGTH_LONG).show()
        })

        val numberText: TextView = findViewById(R.id.number_text)
        val countButton: Button = findViewById(R.id.count_button)
        countButton.setOnClickListener(View.OnClickListener {
            count++
            numberText.text = "$count"
        })

        val randomButton: Button = findViewById(R.id.random_button)
        getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                val value = it.data?.getIntExtra("count", -1)?:""
                count = value.toString().toInt()
                numberText.text = value.toString()
            }
        }

        randomButton.setOnClickListener {
            val intent = Intent(this, ChangeActivity::class.java)
            intent.putExtra("count", count)
            getResultText.launch(intent)
        }
    }
}