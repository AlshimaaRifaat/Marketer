package com.example.themarketer.data.model.AllSection


import com.google.gson.annotations.SerializedName

data class AllSectionResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)