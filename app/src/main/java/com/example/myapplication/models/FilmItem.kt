package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class FilmList(@SerializedName("results") val results:  ArrayList<FilmData>)
data class FilmData(val title: String, val episode_id: String, val release_date: String)