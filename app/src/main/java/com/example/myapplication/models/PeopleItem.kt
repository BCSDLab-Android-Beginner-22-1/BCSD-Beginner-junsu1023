package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class PeopleList(@SerializedName("results") val results:  ArrayList<PeopleData>)
data class PeopleData(val name: String, val gender: String)