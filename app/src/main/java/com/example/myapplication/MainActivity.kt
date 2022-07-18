package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ViewModelSingleTon.viewModel
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        val recyclerView = binding.recyclerView
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
            addItemDecoration(dividerItemDecoration)
        }

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        val viewModelSingleTon = ViewModelSingleTon
        viewModelSingleTon.viewModel = viewModel

        binding.postAddButton.setOnClickListener {
            val intent = Intent(this, WritePost::class.java)
            startActivity(intent)
            true
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}