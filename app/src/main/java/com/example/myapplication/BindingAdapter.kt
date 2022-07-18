package com.example.myapplication

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun bindRecyclerView(recyclerView: RecyclerView, items: ArrayList<PostData>) {
        if (recyclerView.adapter == null) recyclerView.adapter = PostAdapter()

        val postAdapter = recyclerView.adapter as PostAdapter

        postAdapter.dataSet = items
        postAdapter.notifyDataSetChanged()
    }
}