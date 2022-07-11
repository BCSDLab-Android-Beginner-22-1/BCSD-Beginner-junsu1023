package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val view = binding.root
        setContentView(view)

        val postAdapter = PostAdapter()
        getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                val writer = it.data?.getStringExtra("writer") ?: getString(R.string.no_name)
                val title = it.data?.getStringExtra("title")!!
                val content = it.data?.getStringExtra("contents")!!
                val time = it.data?.getLongExtra("time",0)!!

                postAdapter.addPost(PostData(writer, title, content, time))

                val recyclerView = binding.recyclerView
                recyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = postAdapter
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                }
            }
        }

        val postAddButton = binding.postAddButton
        postAddButton.setOnClickListener {
            val intent = Intent(this, WritePost::class.java)
            startActivity(intent)
        }

        postAdapter.setOnItemClickListener {  }

        postAdapter.setOnItemLongClickListener { postAdapter.removePost(it) }
    }
}