package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityWriteBinding

class WritePost: AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val writer = binding.writerTextView.toString()
        val title = binding.titleTextView.toString()
        val contents = binding.contentTextView.toString()
        Log.e("test", "$writer, $title $contents")

        val completeButton = binding.postCompleteButton

        completeButton.setOnClickListener {
            println("$writer, $title, $contents")
            val intent = intent.apply{
                putExtra("writer", writer)
                putExtra("title", title)
                putExtra("contents", contents)
                putExtra("time", System.currentTimeMillis())
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}