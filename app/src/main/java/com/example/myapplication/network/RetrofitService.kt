package com.example.myapplication.network

import com.example.myapplication.models.FilmList
import com.example.myapplication.models.PeopleList
import com.example.myapplication.models.PlanetList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("films")
    suspend fun getFilmDataFromApi(@Query("page") page: String): FilmList

    @GET("people")
    suspend fun getPeopleDataFromApi(@Query("page") page: String): PeopleList

    @GET("planets")
    suspend fun getPlanetDataFromApi(@Query("page") page: String): PlanetList
}