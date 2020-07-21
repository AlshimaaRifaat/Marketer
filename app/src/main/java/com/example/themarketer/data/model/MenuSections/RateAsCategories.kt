package com.example.themarketer.data.model.MenuSections


import com.google.gson.annotations.SerializedName

data class RateAsCategories(
    @SerializedName("Average")
    var average: Float,
    @SerializedName("Excellent")
    var excellent: Float,
    @SerializedName("Poor")
    var poor: Float,
    @SerializedName("Terrible")
    var terrible: Float,
    @SerializedName("Very Good")
    var veryGood: Float
)