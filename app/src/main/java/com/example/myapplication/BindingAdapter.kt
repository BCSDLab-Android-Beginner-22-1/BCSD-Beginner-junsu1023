package com.example.myapplication

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("items")

    fun setItems(recyclerView: RecyclerView, items: ArrayList<PostData>) {
        if(recyclerView.adapter == null) recyclerView.adapter = PostAdapter()

        val postAdapter = recyclerView.adapter as PostAdapter

        postAdapter.postData = items
        postAdapter.notifyDataSetChanged()
    }
}