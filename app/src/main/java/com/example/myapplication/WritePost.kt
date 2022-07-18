package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.ViewModelSingleTon.viewModel
import com.example.myapplication.databinding.ActivityWriteBinding
import java.text.SimpleDateFormat
import java.util.*

class WritePost: AppCompatActivity() {
    private val GALLEY_CODE = 10
    private lateinit var binding: ActivityWriteBinding
    private lateinit var postData: PostData
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        if(intent.extras != null) {
            println("success")
            position = intent.getIntExtra("position", 0)
            postData = viewModel.getPost(position)
        }
        else {
            postData = PostData("", "", "", "")
        }

        binding.post = postData
        binding.activity = this
    }

    fun addPost() {
        val title = binding.titleTextView.text.toString()
        val writer = binding.writerTextView.text.toString()
        val content = binding.contentTextView.text.toString()

        val date = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA)
        val time = sdf.format(date).toString()
        postData = PostData(title, writer, content, time)
        viewModel.addData(postData, position)
        finish()
    }

    fun addPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, GALLEY_CODE)
    }
}