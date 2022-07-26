package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.FilmList
import com.example.myapplication.network.RetroInstance
import com.example.myapplication.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmViewModel: ViewModel() {
    private var recyclerListLiveData : MutableLiveData<FilmList> = MutableLiveData()

    fun getFilmListObserver(): MutableLiveData<FilmList> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response  = retroInstance.getFilmDataFromApi("1")
            recyclerListLiveData.postValue(response)
        }
    }
}