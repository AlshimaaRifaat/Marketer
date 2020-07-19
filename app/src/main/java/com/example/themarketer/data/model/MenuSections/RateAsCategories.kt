package com.example.themarketer.data.model.MenuSections


import com.google.gson.annotations.SerializedName

data class RateAsCategories(
    @SerializedName("Average")
    var average: Int,
    @SerializedName("Excellent")
    var excellent: Int,
    @SerializedName("Poor")
    var poor: Int,
    @SerializedName("Terrible")
    var terrible: Int,
    @SerializedName("Very Good")
    var veryGood: Int
)