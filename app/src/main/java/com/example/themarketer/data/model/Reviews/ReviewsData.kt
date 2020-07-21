package com.example.themarketer.data.model.Reviews


import com.google.gson.annotations.SerializedName

data class ReviewsData(
    @SerializedName("body")
    var body: String,
    @SerializedName("customer name")
    var customerName: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("star")
    var star: String
)