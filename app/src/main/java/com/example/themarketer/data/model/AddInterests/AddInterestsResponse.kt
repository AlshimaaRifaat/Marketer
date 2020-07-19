package com.example.themarketer.data.model.AddInterests


import com.google.gson.annotations.SerializedName

data class AddInterestsResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)