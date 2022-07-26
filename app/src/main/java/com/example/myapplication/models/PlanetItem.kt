package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class PlanetList(@SerializedName("results") val results:  ArrayList<PlanetData>)
data class PlanetData(val name: String, val population: String)