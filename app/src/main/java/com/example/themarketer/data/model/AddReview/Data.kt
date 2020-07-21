package com.example.themarketer.data.model.AddReview


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("body")
    var body: String,
    @SerializedName("customer name")
    var customerName: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("star")
    var star: String
)