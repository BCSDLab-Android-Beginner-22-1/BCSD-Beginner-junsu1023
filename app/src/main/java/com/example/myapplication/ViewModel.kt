package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewModel: ViewModel() {
    private val postLiveData = MutableLiveData<ArrayList<PostData>>()
    val postData: LiveData<ArrayList<PostData>> get() = postLiveData
    private var items = ArrayList<PostData>()

    init {
        postLiveData.value = items
    }

    fun addData(post: PostData, position: Int) {


        when(position) {
            -1 -> {
                items.add(post)
                postLiveData.value = items
            }
            else -> {
                items[position] = post
                postLiveData.value = items
            }
        }
    }

    fun removePost(position: Int) {
        items.removeAt(position)
        postLiveData.value = items
    }

    fun getPost(position: Int): PostData {
        return items[position]
    }
}