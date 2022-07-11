package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityWriteBinding

class WritePost: AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private lateinit var postData: PostData
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        if(intent.extras != null) {
            position = intent.getIntExtra("position", 0)
            postData = ViewModel().getPost(position)
        }
        else {
            postData = PostData("", "", "", "")
        }
        binding.post = postData
        binding.writeActivity = this
    }

    fun addPost() {
        ViewModel().addPost(postData, position)
        finish()
    }
}