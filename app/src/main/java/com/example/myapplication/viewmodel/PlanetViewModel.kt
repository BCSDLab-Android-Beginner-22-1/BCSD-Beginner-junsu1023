package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.PlanetList
import com.example.myapplication.network.RetroInstance
import com.example.myapplication.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetViewModel: ViewModel() {
    private var recyclerListLiveData : MutableLiveData<PlanetList> = MutableLiveData()

    fun getPeopleListObserver(): MutableLiveData<PlanetList> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response  = retroInstance.getPlanetDataFromApi("1")
            recyclerListLiveData.postValue(response)
        }
    }
}