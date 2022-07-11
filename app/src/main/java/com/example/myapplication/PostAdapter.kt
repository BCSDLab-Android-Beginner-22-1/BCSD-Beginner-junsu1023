package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PostItemBinding

class PostAdapter: RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var postData = mutableListOf<PostData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    = ViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postData[position], position)
    }

    override fun getItemCount(): Int = postData.size

    class ViewHolder(private val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(postData: PostData, position: Int) {
            binding.post = postData

            binding.postInfoView.setOnClickListener {
                val intent = Intent(binding.root.context, WritePost::class.java)
                intent.putExtra("position", position)
                binding.root.context.startActivity(intent)
            }

            binding.postInfoView.setOnLongClickListener {
                ViewModel().removePost(position)
                true
            }
        }
    }
}