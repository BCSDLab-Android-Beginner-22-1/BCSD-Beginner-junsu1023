package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.PeopleList
import com.example.myapplication.network.RetroInstance
import com.example.myapplication.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleViewModel: ViewModel() {
    private var recyclerListLiveData : MutableLiveData<PeopleList> = MutableLiveData()

    fun getPeopleListObserver(): MutableLiveData<PeopleList> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response  = retroInstance.getPeopleDataFromApi("1")
            recyclerListLiveData.postValue(response)
        }
    }
}