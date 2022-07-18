package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ViewModelSingleTon.viewModel
import com.example.myapplication.databinding.PostItemBinding

class PostAdapter: RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var dataSet = mutableListOf<PostData>()

    class ViewHolder(private val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(info: PostData, position: Int) {
            binding.post = info

            binding.postInfoView.setOnClickListener {
                val intent = Intent(binding.root.context, WritePost::class.java)
                intent.putExtra("position", position)
                binding.root.context.startActivity(intent)
            }

            binding.postInfoView.setOnLongClickListener {
                viewModel.removePost(position)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(PostItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], position)
    }

    override fun getItemCount(): Int = dataSet.size
}