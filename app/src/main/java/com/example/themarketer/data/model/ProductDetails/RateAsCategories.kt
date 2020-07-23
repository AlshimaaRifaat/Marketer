package com.example.themarketer.data.model.ProductDetails


import com.google.gson.annotations.SerializedName

data class RateAsCategories(
    @SerializedName("Average")
    var average: Double,
    @SerializedName("Excellent")
    var excellent: Double,
    @SerializedName("Poor")
    var poor: Double,
    @SerializedName("Terrible")
    var terrible: Int,
    @SerializedName("Very Good")
    var veryGood: Double
)