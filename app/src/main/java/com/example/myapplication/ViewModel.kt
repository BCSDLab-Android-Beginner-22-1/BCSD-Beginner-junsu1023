package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewModel: ViewModel() {
    private val postList = MutableLiveData<ArrayList<PostData>>()
    val poList: LiveData<ArrayList<PostData>> get() = postList

    private val items = ArrayList<PostData>()

    init { postList.value = items }

    fun addPost(postData: PostData, position: Int) {
        val date = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA)
        val writeTime = sdf.format(date)

        postData.time = writeTime

        if(position == -1) {
            items.add(postData)
            postList.value = items
        }
        else {
            items[position] = postData
            postList.value = items
        }
    }

    fun removePost(position: Int) {
        items.removeAt(position)
        postList.value = items
    }

    fun getPost(position: Int): PostData = items[position]
}