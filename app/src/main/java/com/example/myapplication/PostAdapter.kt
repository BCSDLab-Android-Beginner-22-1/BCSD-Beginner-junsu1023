package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PostItemBinding

class PostAdapter: RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private val postList = mutableListOf<PostData>()
    lateinit var binding: PostItemBinding
    lateinit var onItemClickListener: OnItemClickListener
    lateinit var onItemLongClickListener: OnItemLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    = ViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])

        binding.postInfoView.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }

        binding.postInfoView.setOnLongClickListener {
            onItemLongClickListener.onItemLongClick(position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int = postList.size

    class ViewHolder(private val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PostData) {
            binding.itemTitleTextView.text = data.title
            val sb = StringBuilder()
            for(i in 0 until 20) sb.append(data.content[i])
            binding.itemContentsTextView.text = sb.toString()
            binding.itemWriterTextView.text = data.writer
            binding.itemTimeTextView.text = System.currentTimeMillis().toString()
        }
    }

    fun addPost(postData: PostData) {
        postList.add(postData)
        notifyDataSetChanged()
    }

    fun removePost(position: Int) {
        postList.removeAt(position)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    inline fun setOnItemClickListener(crossinline onItemClick: (Int) -> (Unit)) {
        onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                onItemClick(position)
            }
        }
    }

    inline fun setOnItemLongClickListener(crossinline onItemLongClick: (Int) -> Unit) {
        onItemLongClickListener = object : OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                onItemLongClick(position)
            }
        }
    }
}